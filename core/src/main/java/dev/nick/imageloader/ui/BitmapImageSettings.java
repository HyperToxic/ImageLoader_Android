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

package dev.nick.imageloader.ui;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import dev.nick.imageloader.ui.animator.ImageAnimator;

public class BitmapImageSettings extends ImageSettings<Bitmap> {

    Bitmap mBitmap;

    public BitmapImageSettings(ImageAnimator<Bitmap> animator, @NonNull ImageChair<Bitmap> settable, Bitmap bitmap) {
        super(animator, settable);
        this.mBitmap = bitmap;
    }

    @Override
    protected void apply() {
        if (mBitmap != null) {
            mSeat.seat(mBitmap);
            if (mAnimator != null) {
                mAnimator.animate(mSeat);
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + " BitmapImageSettings{" +
                "mBitmap=" + mBitmap +
                '}';
    }
}
