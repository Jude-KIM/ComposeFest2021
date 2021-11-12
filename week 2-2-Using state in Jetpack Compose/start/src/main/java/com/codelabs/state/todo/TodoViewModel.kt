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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    var items = mutableStateListOf<TodoItem>()
        private set

    private var currentPos by mutableStateOf(-1)

    val currentItem: TodoItem?
    get() = items.getOrNull(currentPos)

    fun addItem(item: TodoItem) {
        items.add(item)
    }

    fun removeItem(item: TodoItem) {
        items.remove(item)
        onEditDone()
    }

    fun onEditItem(item: TodoItem) {
        currentPos = items.indexOf(item)
    }

    fun onEditDone() {
        currentPos = -1
    }

    fun onEditItemChange(item: TodoItem) {
        items[currentPos] = item
    }
}
