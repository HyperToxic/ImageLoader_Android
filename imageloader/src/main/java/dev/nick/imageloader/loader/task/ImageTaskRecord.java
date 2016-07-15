/*
 * Copyright (c) 2016 Nick Guo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.nick.imageloader.loader.task;

public class ImageTaskRecord extends TaskRecord {

    private int settableId;

    public ImageTaskRecord(int settableId, int taskId) {
        super(taskId);
        this.settableId = settableId;
    }

    public int getSettableId() {
        return settableId;
    }

    @Override
    public String toString() {
        return "ImageTaskRecord{" +
                "settableId=" + settableId +
                "} " + super.toString();
    }
}
