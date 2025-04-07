/**
 * @license
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

import {SpecVersion} from '../dynamiccolor/color_spec.js';
import {DynamicScheme, Platform} from '../dynamiccolor/dynamic_scheme';
import {Variant} from '../dynamiccolor/variant.js';
import {Hct} from '../hct/hct.js';
import {TonalPalette} from '../palettes/tonal_palette.js';

/** A Dynamic Color theme that is near grayscale. */
export class SchemeNeutral extends DynamicScheme {
  private static readonly DEFAULT_SPEC_VERSION = '2021';
  private static readonly DEFAULT_PLATFORM = 'phone';

  constructor(
      sourceColorHct: Hct, isDark: boolean, contrastLevel: number,
      specVersion: SpecVersion = SchemeNeutral.DEFAULT_SPEC_VERSION,
      platform: Platform = SchemeNeutral.DEFAULT_PLATFORM) {
    super({
      sourceColorHct,
      variant: Variant.NEUTRAL,
      contrastLevel,
      isDark,
      platform,
      specVersion,
    });
  }
}
