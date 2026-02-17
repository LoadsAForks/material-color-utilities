/*
 * Copyright 2026 Google LLC
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
package scheme

import dynamiccolor.ColorSpec
import dynamiccolor.DynamicScheme
import dynamiccolor.Variant
import hct.Hct
import palettes.TonalPalette

/** A Dynamic Color theme with 2 source colors. */
class SchemeCmf(
  sourceColorHct: Hct,
  isDark: Boolean,
  contrastLevel: Double,
  specVersion: ColorSpec.SpecVersion = ColorSpec.SpecVersion.SPEC_2026,
  platform: Platform = DEFAULT_PLATFORM,
  extraSourceColorsHct: List<Hct> = emptyList(),
) :
  DynamicScheme(
    sourceColorHct = sourceColorHct,
    variant = Variant.CMF,
    isDark = isDark,
    contrastLevel = contrastLevel,
    platform = platform,
    specVersion = specVersion,
    primaryPalette = TonalPalette.fromHueAndChroma(sourceColorHct.hue, sourceColorHct.chroma),
    secondaryPalette =
      TonalPalette.fromHueAndChroma(sourceColorHct.hue, sourceColorHct.chroma * 0.5),
    tertiaryPalette = tertiaryPalette(sourceColorHct, extraSourceColorsHct),
    neutralPalette = TonalPalette.fromHueAndChroma(sourceColorHct.hue, sourceColorHct.chroma * 0.2),
    neutralVariantPalette =
      TonalPalette.fromHueAndChroma(sourceColorHct.hue, sourceColorHct.chroma * 0.2),
    errorPalette = TonalPalette.fromHueAndChroma(23.0, sourceColorHct.chroma.coerceAtLeast(50.0)),
    extraSourceColorsHct = extraSourceColorsHct,
  ) {
  init {
    require(specVersion == ColorSpec.SpecVersion.SPEC_2026) {
      "SchemeCmf can only be used with spec version 2026."
    }
  }
}

private fun tertiaryPalette(sourceColorHct: Hct, extraSourceColorsHct: List<Hct>): TonalPalette {
  val secondarySourceColorHct = extraSourceColorsHct.firstOrNull() ?: sourceColorHct
  return if (sourceColorHct.toInt() == secondarySourceColorHct.toInt()) {
    TonalPalette.fromHueAndChroma(sourceColorHct.hue, sourceColorHct.chroma * 0.75)
  } else {
    TonalPalette.fromHueAndChroma(secondarySourceColorHct.hue, secondarySourceColorHct.chroma)
  }
}
