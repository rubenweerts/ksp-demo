package nl.pinch.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid

class PinchEmailSymbolVisitor(
    codeGenerator: CodeGenerator,
) : KSVisitorVoid() {

    private val generator = PinchEmailGenerator(codeGenerator)

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        generator.generate(classDeclaration)
    }
}