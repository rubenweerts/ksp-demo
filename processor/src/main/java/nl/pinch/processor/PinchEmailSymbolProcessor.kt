package nl.pinch.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import nl.pinch.annotations.PinchEmail

class PinchEmailSymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
) : SymbolProcessor {
    private val visitor = PinchEmailSymbolVisitor(codeGenerator)

    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.warn("process called")
        val annotation = PinchEmail::class
        val annotationName = annotation.qualifiedName!!
        val annotationSimpleName = annotation.simpleName!!

        resolver
            .getSymbolsWithAnnotation(annotationName)
            .filterIsInstance<KSClassDeclaration>()
            .filter { it.validate() }
            .filter { symbol ->
                // Check if annotation is on a data class
                val isDataClass = symbol.modifiers.any { it.name.lowercase() == "data" }
                if (!isDataClass) {
                    logger.error("@$annotationSimpleName needs to be on a data class", symbol)
                }
                isDataClass
            }
            .forEach {
                it.accept(visitor, Unit)
            }

        // Return all that need further processing
        return emptyList()
    }
}