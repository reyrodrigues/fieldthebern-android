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

package com.berniesanders.fieldthebern.providers;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.berniesanders.fieldthebern.db.SearchMatrixCursor;

public class SearchProvider extends ContentProvider {

  public static final String SEARCH_TABLE = "search";
  public static final String PAGES_SUB_TABLE = "pages";

  public static final String AUTHORITY = "com.berniesanders.fieldthebern.providers.SearchProvider";

  public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
  public static final Uri SEARCH_URI = Uri.parse("content://" + AUTHORITY + "/" + SEARCH_TABLE);
  public static final Uri SEARCH_PAGES_URI =
      Uri.parse("content://" + AUTHORITY + "/" + SEARCH_TABLE + "/" + PAGES_SUB_TABLE);

  //UriMatcher ints
  private static final int SEARCH_PAGES = 0;
  private static final int GET_PAGE = 1;
  private static final int SEARCH_SUGGEST = 2;

  private static final UriMatcher uriMatcher = buildUriMatcher();
  private static SearchMatrixCursor matrixCursor = null;

  private static UriMatcher buildUriMatcher() {
    UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    // to get page...
    matcher.addURI(AUTHORITY, SEARCH_TABLE, SEARCH_PAGES);
    matcher.addURI(AUTHORITY, SEARCH_TABLE + "/#", GET_PAGE);

    // to get suggestions...
    matcher.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY, SEARCH_SUGGEST);
    matcher.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY + "/*", SEARCH_SUGGEST);

    return matcher;
  }

  @Override
  public boolean onCreate() {
    return true;
  }

  @Override
  public String getType(Uri uri) {
    return null;
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {

    SearchMatrixCursor returnCursor = new SearchMatrixCursor();

    switch (uriMatcher.match(uri)) {

      case SEARCH_PAGES:
        break;

      case GET_PAGE:
        break;

      case SEARCH_SUGGEST:
        returnCursor.getSearchSuggestionCursor(uri.getLastPathSegment());
        break;

      default:
        throw new IllegalArgumentException("Unknown Uri: " + uri);
    }
    return returnCursor;
  }

  //------------------------------------------------------------
  // Unsupported operations
  //------------------------------------------------------------

  @Override
  public Uri insert(Uri uri, ContentValues values) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    throw new UnsupportedOperationException();
  }
}
