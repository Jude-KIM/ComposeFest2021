/*
 * Copyright (C) 2020 The Android Open Source Project
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

package com.codelabs.state.todo

import com.codelabs.state.util.generateRandomTodoItem
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TodoViewModelTest {
    // TODO: Write tests

    @Test
    fun whenRemovingItem_UpdatesList() {
        val viewModel = TodoViewModel()
        val item1 = generateRandomTodoItem()
        val item2 = generateRandomTodoItem()
        viewModel.addItem(item1)
        viewModel.addItem(item2)

        viewModel.removeItem(item1)
        assertThat(viewModel.items).isEqualTo(listOf(item2))
    }

    @Test
    fun whenEditingItem_updatesAreShownInItemAndList() {
        val subject = TodoViewModel()
        val item1 = generateRandomTodoItem()
        val item2 = generateRandomTodoItem()
        subject.addItem(item1)
        subject.addItem(item2)
        subject.onEditItem(item1)
        val expected = item1.copy(task = "Update for test case")
        subject.onEditItemChange(expected)
        assertThat(subject.items).isEqualTo(listOf(expected, item2))
        assertThat(subject.currentItem).isEqualTo(expected)
    }
}
