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

package scheme;

import dynamiccolor.ColorSpec.SpecVersion;
import dynamiccolor.DynamicScheme;
import dynamiccolor.Variant;
import hct.Hct;
import palettes.TonalPalette;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** A Dynamic Color theme with 2 source colors. */
public class SchemeCmf extends DynamicScheme {

  public SchemeCmf(Hct sourceColorHct, boolean isDark, double contrastLevel) {
    this(
        sourceColorHct,
        isDark,
        contrastLevel,
        SpecVersion.SPEC_2026,
        DEFAULT_PLATFORM,
        new ArrayList<>());
  }

  public SchemeCmf(
      Hct sourceColorHct,
      boolean isDark,
      double contrastLevel,
      SpecVersion specVersion,
      Platform platform,
      List<Hct> extraSourceColorsHct) {
    super(
        sourceColorHct,
        Variant.CMF,
        isDark,
        contrastLevel,
        platform,
        specVersion,
        TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), sourceColorHct.getChroma()),
        TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), sourceColorHct.getChroma() * 0.5),
        tertiaryPalette(sourceColorHct, extraSourceColorsHct),
        TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), sourceColorHct.getChroma() * 0.2),
        TonalPalette.fromHueAndChroma(sourceColorHct.getHue(), sourceColorHct.getChroma() * 0.2),
        Optional.of(
            TonalPalette.fromHueAndChroma(23.0, Math.max(sourceColorHct.getChroma(), 50.0))),
        extraSourceColorsHct);
    if (specVersion != SpecVersion.SPEC_2026) {
      throw new IllegalArgumentException("SchemeCmf can only be used with spec version 2026.");
    }
  }

  private static TonalPalette tertiaryPalette(Hct sourceColorHct, List<Hct> extraSourceColorsHct) {
    Hct secondarySourceColorHct =
        extraSourceColorsHct.isEmpty() ? sourceColorHct : extraSourceColorsHct.get(0);
    if (sourceColorHct.toInt() == secondarySourceColorHct.toInt()) {
      return TonalPalette.fromHueAndChroma(
          sourceColorHct.getHue(), sourceColorHct.getChroma() * 0.75);
    } else {
      return TonalPalette.fromHueAndChroma(
          secondarySourceColorHct.getHue(), secondarySourceColorHct.getChroma());
    }
  }
}
