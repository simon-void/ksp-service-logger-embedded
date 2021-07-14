package com.example.annotation

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate

class ServiceLoggerProcessor(
    private val options: Map<String, String>,
//    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val qualifiedAnnotation = "org.springframework.stereotype.Service"

        val (validSymbols, invalidSymbols) =
            resolver.getSymbolsWithAnnotation(qualifiedAnnotation)
                .partition { it.validate() }


        val classDeclarations: List<KSClassDeclaration> = validSymbols.filterIsInstance<KSClassDeclaration>()
        logger.info("ServiceLoggerProcessor options: $options")
        logger.info("found ${classDeclarations.size} classes annotated $qualifiedAnnotation")
        val concatenatedClassnames = classDeclarations.mapNotNull { it.qualifiedName }.joinToString {it.asString()}
        logger.info(concatenatedClassnames)

        logger.error("options: $options")

        // intential throwing an exception to debug why logging doesn't show up
//        throw Throwable("Processor found ${classDeclarations.size} classes annotated by $qualifiedAnnotation: $concatenatedClassnames")
        throw Throwable("options: $options")
//        return invalidSymbols
    }

    class ServiceLoggerProcessorProvider : SymbolProcessorProvider {

        override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
            return ServiceLoggerProcessor(
                options = environment.options,
//                codeGenerator = environment.codeGenerator,
                logger = environment.logger
            )
        }
    }
}
