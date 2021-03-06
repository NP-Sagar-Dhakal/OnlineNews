/*
  spaRSS
  <p/>
  Copyright (c) 2015-2016 Arnaud Renaud-Goud
  Copyright (c) 2012-2015 Frederic Julian
  <p/>
  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
  <p/>
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  <p/>
  You should have received a copy of the GNU General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.newstoday.rssfeedreader.view;

import android.view.View;

/**
 * Implement to handle an item being dragged.
 *
 * @author Eric Harlow
 */
public interface DragNDropListener {
    /**
     * Called when a drag starts.
     *
     * @param itemView the view of the item to be dragged i.e. the drag view
     */
    void onStartDrag(View itemView);

    /**
     * Called when a drag is to be performed.
     */
    void onDrag();

    /**
     * Called when a drag stops. Any changes in onStartDrag need to be undone here so that the view can be used in the list again.
     */
    void onStopDrag();

    /**
     * Called when an item is to be dropped.
     *
     * @param flatPosFrom index item started at.
     * @param flatPosTo   index to place item at.
     */
    void onDrop(int flatPosFrom, int flatPosTo);
}
