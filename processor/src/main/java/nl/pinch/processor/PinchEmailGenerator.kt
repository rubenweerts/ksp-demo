package nl.pinch.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo

class PinchEmailGenerator(
    val codeGenerator: CodeGenerator,
) {
    fun generate(classDeclaration: KSClassDeclaration) {
        val packageName = classDeclaration.packageName.asString()
        val fileName = "${classDeclaration.simpleName.asString()}PinchEmailExt"
        val receiverType = classDeclaration.asType(emptyList()).toTypeName()


        val fileSpec = FileSpec.builder(
            packageName = packageName,
            fileName = fileName
        ).apply {
            addFunction(FunSpec.builder("pinchEmail")
                .receiver(receiverType)
                .returns(String::class)
                .addStatement("return \"\${firstName.lowercase()}.\${lastName.lowercase()}@pinch.nl\"")
                .build())

        }.build()

        fileSpec.writeTo(codeGenerator, true)
    }
}
