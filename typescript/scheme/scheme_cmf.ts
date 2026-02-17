/**
 * @license
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

import {SpecVersion} from '../dynamiccolor/color_spec.js';
import {DynamicScheme, Platform} from '../dynamiccolor/dynamic_scheme';
import {Variant} from '../dynamiccolor/variant.js';
import {Hct} from '../hct/hct.js';
import {TonalPalette} from '../palettes/tonal_palette.js';

/**
 * A Dynamic Color theme with 2 source colors.
 */
export class SchemeCmf extends DynamicScheme {
  constructor(
      sourceColorHct: Hct, isDark: boolean, contrastLevel: number,
      specVersion: SpecVersion = '2026',
      platform: Platform = DynamicScheme.DEFAULT_PLATFORM,
      extraSourceColorsHct: Hct[] = []) {
    if (specVersion !== '2026') {
      throw new Error('SchemeCmf can only be used with spec version 2026.');
    }
    const secondarySourceColorHct = extraSourceColorsHct[0] ?? sourceColorHct;

    const primaryPalette = TonalPalette.fromHueAndChroma(
        sourceColorHct.hue, sourceColorHct.chroma);
    const secondaryPalette = TonalPalette.fromHueAndChroma(
        sourceColorHct.hue, sourceColorHct.chroma * 0.5);
    const errorPalette = TonalPalette.fromHueAndChroma(
        23.0, Math.max(sourceColorHct.chroma, 50.0));
    const neutralPalette = TonalPalette.fromHueAndChroma(
        sourceColorHct.hue, sourceColorHct.chroma * 0.2);
    const neutralVariantPalette = TonalPalette.fromHueAndChroma(
        sourceColorHct.hue, sourceColorHct.chroma * 0.2);

    let tertiaryPalette: TonalPalette;

    if (sourceColorHct.toInt() === secondarySourceColorHct.toInt()) {
      tertiaryPalette = TonalPalette.fromHueAndChroma(
          sourceColorHct.hue, sourceColorHct.chroma * 0.75);
    } else {
      tertiaryPalette = TonalPalette.fromHueAndChroma(
          secondarySourceColorHct.hue, secondarySourceColorHct.chroma);
    }

    super({
      sourceColorHct,
      variant: Variant.CMF,
      contrastLevel,
      isDark,
      platform,
      specVersion,
      primaryPalette,
      secondaryPalette,
      tertiaryPalette,
      neutralPalette,
      neutralVariantPalette,
      errorPalette,
      extraSourceColorsHct,
    });
  }
}
