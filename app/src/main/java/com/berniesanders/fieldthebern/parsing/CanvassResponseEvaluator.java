/*
 * Copyright (c) 2016 - Bernie 2016, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.berniesanders.fieldthebern.parsing;

import com.berniesanders.fieldthebern.models.CanvassResponse;
import timber.log.Timber;

public class CanvassResponseEvaluator {

  /**
   * Returns the API compatible response that maps to the selected string
   *
   * <item>(select support level)</item>
   * <item>Strongly for Bernie</item>
   * <item>Leaning for Bernie</item>
   * <item>Undecided</item>
   * <item>Leaning against Bernie</item>
   * <item>Strongly against Bernie</item>
   *
   * TODO better way to do this?!
   */
  @CanvassResponse.Response
  public static String getResponse(String selectedItem, String[] interest) {
    if (selectedItem.equals(interest[0])) {
      Timber.e("CanvassResponseEvaluator.getResponse() called but selection is not valid");
      return CanvassResponse.UNKNOWN;
    } else if (selectedItem.equals(interest[1])) {
      return CanvassResponse.STRONGLY_FOR;
    } else if (selectedItem.equals(interest[2])) {
      return CanvassResponse.LEANING_FOR;
    } else if (selectedItem.equals(interest[3])) {
      return CanvassResponse.UNDECIDED;
    } else if (selectedItem.equals(interest[4])) {
      return CanvassResponse.LEANING_AGAINST;
    } else if (selectedItem.equals(interest[5])) {
      return CanvassResponse.STRONGLY_AGAINST;
    } else {
      Timber.e("CanvassResponseEvaluator.getResponse() called but selection is default?");
      return CanvassResponse.UNKNOWN;
    }
  }

  /**
   * Returns the user displayable string based off the supplied response
   *
   * <item>(select support level)</item>
   * <item>Strongly for Bernie</item>
   * <item>Leaning for Bernie</item>
   * <item>Undecided</item>
   * <item>Leaning against Bernie</item>
   * <item>Strongly against Bernie</item>
   *
   * TODO better way to do this?!
   */
  public static String getText(@CanvassResponse.Response final String response,
      final String[] textArray) {

    switch (response) {
      case CanvassResponse.UNKNOWN:
        return "unknown"; //TODO we need a string for this....
      case CanvassResponse.STRONGLY_FOR:
        return textArray[1];
      case CanvassResponse.LEANING_FOR:
        return textArray[2];
      case CanvassResponse.UNDECIDED:
        return textArray[3];
      case CanvassResponse.LEANING_AGAINST:
        return textArray[4];
      case CanvassResponse.STRONGLY_AGAINST:
        return textArray[5];
      case CanvassResponse.ASKED_TO_LEAVE:
        return "asked to leave"; //TODO we need a string for this....
      case CanvassResponse.NO_ONE_HOME:
        return "No one home"; //TODO we need a string for this....
      default:
        Timber.e("CanvassResponseEvaluator.getText() called but selection is unknown?");
        return "unknown"; //TODO we need a string for this....
    }
  }
}
