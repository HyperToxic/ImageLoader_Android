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

package dev.nick.imageloader.worker;

public class ImageData<X> {

    private ImageSource<X> type;
    private String url;

    public ImageData() {
    }

    public ImageData(ImageSource<X> type, String url) {
        this.type = type;
        this.url = url;
    }

    public ImageSource<X> getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setType(ImageSource<X> type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
