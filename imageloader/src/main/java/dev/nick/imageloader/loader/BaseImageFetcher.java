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

package dev.nick.imageloader.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import dev.nick.imageloader.LoaderConfig;
import dev.nick.imageloader.loader.result.BitmapResult;
import dev.nick.imageloader.loader.result.Cause;
import dev.nick.imageloader.loader.result.ErrorListener;
import dev.nick.logger.LoggerManager;

class BaseImageFetcher implements ImageFetcher {

    static final int UNCONSTRAINED = -1;

    /* Maximum pixels size for created bitmap. */
    static final int MAX_NUM_PIXELS_THUMBNAIL = 512 * 512;

    protected PathSplitter<String> splitter;

    protected Context context;

    protected LoaderConfig loaderConfig;

    protected boolean debug;

    public BaseImageFetcher(PathSplitter<String> splitter) {
        this.splitter = splitter;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public void fetchFromUrl(@NonNull String url,
                             @NonNull DecodeSpec decodeSpec,
                             @Nullable ProgressListener<BitmapResult> progressListener,
                             @Nullable ErrorListener errorListener)
            throws Exception {
        if (debug) {
            LoggerManager.getLogger(getClass()).funcEnter();
        }
    }

    @Override
    public ImageFetcher prepare(Context context, LoaderConfig config) {
        this.context = context;
        this.loaderConfig = config;
        return this;
    }

    protected BitmapResult newResult(Bitmap bitmap) {
        BitmapResult result = new BitmapResult();
        result.result = bitmap;
        return result;
    }

    protected void callOnStart(ProgressListener<BitmapResult> listener) {
        if (listener != null) listener.onStartLoading();
    }

    protected void callOnComplete(ProgressListener<BitmapResult> listener, BitmapResult result) {
        if (listener != null) listener.onComplete(result);
    }

    protected void callOnError(ErrorListener listener, @NonNull Cause cause) {
        if (listener != null) listener.onError(cause);
    }

    protected void callOnProgress(ProgressListener<BitmapResult> listener, int progress) {
        if (listener != null) listener.onProgressUpdate(progress);
    }

    /*
     * Compute the sample size as a function of minSideLength
     * and maxNumOfPixels.
     * minSideLength is used to specify that minimal width or height of a
     * bitmap.
     * maxNumOfPixels is used to specify the maximal size in pixels that is
     * tolerable in terms of memory usage.
     *
     * The function returns a sample size based on the constraints.
     * Both size and minSideLength can be passed in as IImage.UNCONSTRAINED,
     * which indicates no care of the corresponding constraint.
     * The functions prefers returning a sample size that
     * generates a smaller bitmap, unless minSideLength = IImage.UNCONSTRAINED.
     *
     * Also, the function rounds up the sample size to a power of 2 or multiple
     * of 8 because BitmapFactory only honors sample size this way.
     * For example, BitmapFactory downsamples an image by 2 even though the
     * request is 3. So we round up the sample size to avoid OOM.
     */
    protected int computeSampleSize(BitmapFactory.Options options,
                                    int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private int computeInitialSampleSize(BitmapFactory.Options options,
                                         int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == UNCONSTRAINED) ? 1 :
                (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == UNCONSTRAINED) ? 128 :
                (int) Math.min(Math.floor(w / minSideLength),
                        Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == UNCONSTRAINED) &&
                (minSideLength == UNCONSTRAINED)) {
            return 1;
        } else if (minSideLength == UNCONSTRAINED) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }
}
