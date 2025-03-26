/*
 * Copyright 2022 Google LLC
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

package scheme;

import static dynamiccolor.DynamicScheme.Platform.PHONE;

import dynamiccolor.ColorSpec.SpecVersion;
import dynamiccolor.ColorSpecs;
import dynamiccolor.DynamicScheme;
import dynamiccolor.Variant;
import hct.Hct;

/** A playful theme - the source color's hue does not appear in the theme. */
public class SchemeExpressive extends DynamicScheme {

  private static final SpecVersion DEFAULT_SPEC_VERSION = SpecVersion.SPEC_2021;
  private static final Platform DEFAULT_PLATFORM = PHONE;

  public SchemeExpressive(Hct sourceColorHct, boolean isDark, double contrastLevel) {
    this(sourceColorHct, isDark, contrastLevel, DEFAULT_SPEC_VERSION, DEFAULT_PLATFORM);
  }

  public SchemeExpressive(
      Hct sourceColorHct,
      boolean isDark,
      double contrastLevel,
      SpecVersion specVersion,
      Platform platform) {
    super(
        sourceColorHct,
        Variant.EXPRESSIVE,
        isDark,
        contrastLevel,
        platform,
        specVersion,
        ColorSpecs.get(specVersion)
            .getPrimaryPalette(Variant.EXPRESSIVE, sourceColorHct, isDark, platform, contrastLevel),
        ColorSpecs.get(specVersion)
            .getSecondaryPalette(
                Variant.EXPRESSIVE, sourceColorHct, isDark, platform, contrastLevel),
        ColorSpecs.get(specVersion)
            .getTertiaryPalette(
                Variant.EXPRESSIVE, sourceColorHct, isDark, platform, contrastLevel),
        ColorSpecs.get(specVersion)
            .getNeutralPalette(Variant.EXPRESSIVE, sourceColorHct, isDark, platform, contrastLevel),
        ColorSpecs.get(specVersion)
            .getNeutralVariantPalette(
                Variant.EXPRESSIVE, sourceColorHct, isDark, platform, contrastLevel),
        ColorSpecs.get(specVersion)
            .getErrorPalette(Variant.EXPRESSIVE, sourceColorHct, isDark, platform, contrastLevel));
  }
}
