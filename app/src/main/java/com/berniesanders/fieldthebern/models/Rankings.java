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

package com.berniesanders.fieldthebern.models;

import java.util.ArrayList;

/**
 * // request format
 * {
 * type: <'everyone'|'state'|'friends'>
 * }
 *
 * // response format
 * data: [{
 * id: <ranking_id>,
 * type: 'rankings',
 * attributes: {
 * rank: <rank>,
 * score: <score>,
 * },
 * relationships: {
 * user: { data: { id: <user_id>, type: 'users' } }
 * }
 * }, /once for each ranking/
 * ],
 * included: [{
 * id: <user_id>,
 * type: 'users',
 * attributes: {
 * email: <email>,
 * password: <password>,
 * first_name: <first_name>,
 * last_name: <last_name>,
 * state_code: <state_code>,
 * lat: <geographic_latitude>,
 * lng: <geographic_longitude>,
 * photo_thumb_url: <thumbnail_photo_url>,
 * photo_large_url: <full_size_photo_url>
 * }
 * }, /once for each user/
 * ]
 */
public class Rankings {

  java.util.List<UserData> included = new ArrayList<>();
  java.util.List<Data> data = new ArrayList<>();

  public static class Data {
    long id;
    String type = "rankings";
    Attributes attributes;

    public long id() {
      return this.id;
    }

    public String type() {
      return this.type;
    }

    public Attributes attributes() {
      return this.attributes;
    }

    public Data id(final long id) {
      this.id = id;
      return this;
    }

    public Data type(final String type) {
      this.type = type;
      return this;
    }

    public Data attributes(final Attributes attributes) {
      this.attributes = attributes;
      return this;
    }
  }

  public static class Attributes {
    String rank;
    int score;

    @Override
    public String toString() {
      return "Attributes{" +
          "rank='" + rank + '\'' +
          ", score=" + score +
          '}';
    }

    public String rank() {
      return this.rank;
    }

    public int score() {
      return this.score;
    }

    public Attributes rank(final String rank) {
      this.rank = rank;
      return this;
    }

    public Attributes score(final int score) {
      this.score = score;
      return this;
    }
  }

  static class Relationships {
    User user;

    public User user() {
      return this.user;
    }

    public Relationships user(final User user) {
      this.user = user;
      return this;
    }

    @Override
    public String toString() {
      return "Relationships{" +
          "user=" + user +
          '}';
    }
  }

  @Override
  public String toString() {
    return "Rankings{" +
        "included=" + included +
        ", data=" + data +
        '}';
  }

  public java.util.List<UserData> included() {
    return this.included;
  }

  public java.util.List<Data> data() {
    return this.data;
  }

  public Rankings included(final java.util.List<UserData> included) {
    this.included = included;
    return this;
  }

  public Rankings data(final java.util.List<Data> data) {
    this.data = data;
    return this;
  }
}
