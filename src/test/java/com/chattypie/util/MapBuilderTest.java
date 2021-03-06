/*
 * Copyright 2017 AppDirect, Inc. and/or its affiliates
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chattypie.util;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.entry;

import java.util.Map;

import org.junit.Test;

public class MapBuilderTest {

	private MapBuilder mapBuilder = new MapBuilder();

	@Test
	public void canParse_single_wellFormedEntry() throws Exception {
		Map<String, String> parsedMap = mapBuilder.fromCommaDelimitedKeyValuePairs("k1:s1");

		assertThat(parsedMap).containsOnly(entry("k1", "s1"));
	}

	@Test
	public void canParse_multiple_wellFormedEntry() throws Exception {
		Map<String, String> parsedMap = mapBuilder.fromCommaDelimitedKeyValuePairs("k1:s1,k2:s2,k3:s3");

		assertThat(parsedMap).containsOnly(entry("k1", "s1"), entry("k2", "s2"), entry("k3", "s3"));
	}

	@Test
	public void errorsOut_when_singleMalformedEntry() throws Exception {
		assertThatThrownBy(() -> mapBuilder.fromCommaDelimitedKeyValuePairs("k1:s1:3"))
				.hasMessage("This pair is broken: 'k1:s1:3'. Separate your key from your value with a ':'.")
				.isInstanceOf(IllegalArgumentException.class);
	}
}
