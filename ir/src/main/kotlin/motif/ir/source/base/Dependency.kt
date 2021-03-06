/*
 * Copyright (c) 2018 Uber Technologies, Inc.
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
package motif.ir.source.base

class Dependency(
        val userData: Any?,
        val type: Type,
        val qualifier: Annotation?) : Comparable<Dependency> {

    private val key = Key(type, qualifier)

    private val compareKey: String by lazy {
        "${type.id}-${qualifier?.id}"
    }

    override fun compareTo(other: Dependency): Int {
        return compareKey.compareTo(other.compareKey)
    }

    override fun toString(): String {
        val qualifierString = qualifier?.let { "$it " } ?: ""
        return "$qualifierString $type"
    }

    override fun equals(other: Any?): Boolean {
        val o = other as? Dependency ?: return false
        return key == o.key
    }

    override fun hashCode(): Int {
        return key.hashCode()
    }
}

private data class Key(val type: Type, val qualifier: Annotation?)