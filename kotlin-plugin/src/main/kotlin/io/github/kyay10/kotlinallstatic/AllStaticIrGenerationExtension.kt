/*
 * Copyright (C) 2020 Brian Norman
 * Copyright (C) 2021 Youssef Shoaib
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.kyay10.kotlinallstatic

import io.github.kyay10.kotlinallstatic.utils.*
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.com.intellij.openapi.project.Project
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.builders.irCallConstructor
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.util.*
import org.jetbrains.kotlin.resolve.annotations.JVM_STATIC_ANNOTATION_FQ_NAME

@Suppress("unused")
class AllStaticIrGenerationExtension(
  private val project: Project,
  private val messageCollector: MessageCollector,
  private val compilerConfig: CompilerConfiguration
) : IrGenerationExtension {
  override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
    moduleFragment.lowerWith(object : IrFileTransformerVoidWithContext(pluginContext) {
      override fun visitFunctionNew(declaration: IrFunction): IrStatement {
        addJvmStaticAnnotationIfNeeded(declaration)
        return super.visitFunctionNew(declaration)
      }

      override fun visitPropertyNew(declaration: IrProperty): IrStatement {
        addJvmStaticAnnotationIfNeeded(declaration)
        return super.visitPropertyNew(declaration)
      }

      fun addJvmStaticAnnotationIfNeeded(declaration: IrDeclaration) {
        if (declaration.parent.safeAs<IrClass>()?.kind == ClassKind.OBJECT && declaration !is IrConstructor && declaration.origin != IrDeclarationOrigin.FAKE_OVERRIDE) {
          if (!declaration.hasAnnotation(JVM_STATIC_ANNOTATION_FQ_NAME)) {
            declaration.annotations += declarationIrBuilder.irCallConstructor(
              pluginContext.referenceClass(
                JVM_STATIC_ANNOTATION_FQ_NAME
              )?.constructors?.firstOrNull() ?: return, listOf()
            )
          }
        }
      }
    })
  }
}
