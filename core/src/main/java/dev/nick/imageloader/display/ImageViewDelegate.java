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

package dev.nick.imageloader.display;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Wrapper class for a {@link ImageView}
 */
public class ImageViewDelegate implements ImageSettable {

    private ImageView mImageView;

    public ImageViewDelegate(ImageView imageView) {
        this.mImageView = imageView;
    }

    @Override
    public void setImageBitmap(@NonNull Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }

    @Override
    public boolean setBackgroundDrawable(@Nullable Drawable drawable) {
        return false;
    }

    @Override
    public void setImageResource(int resId) {
        mImageView.setImageResource(resId);
    }

    @Override
    public int getWidth() {
        return mImageView.getWidth();
    }

    @Override
    public int getHeight() {
        return mImageView.getHeight();
    }

    @Override
    public void startAnimation(Animation animation) {
        mImageView.clearAnimation();
        mImageView.startAnimation(animation);
    }

    @Override
    public int hashCode() {
        return mImageView.hashCode();
    }
}