/*
  OnlineNews
  <p/>
  Copyright (c) 2019-2021 Sagar Dhakal
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

package com.newstoday.services;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.newstoday.nepali.news.R;

public class Disclaimer_Dialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
        builder.setTitle("Disclaimer")
                .setMessage("This app is made for those who like to read news and other article and listen online radios,\n" +
                        "        \n\n" +
                        "        To make a good application we should give extra effort to make all services accurate and valid, application may contain inaccuracies. By using this app,\n" +
                        "        you agree that this app assumes no liability for any harm caused by use of this application.\n" +
                        "        \n\n\n" +
                        "        We doesn't own and assume responsibility of any news and sites added on this app. This app uses RSS Feed to gather and display news and sites. News content, Images and their icons are copyright of their respective owner.\n" +
                        "        if any website/content is yours and you don't want to show your content here, please contact us first.\n" +
                        "        \n\n\n" +
                        "        Radio is a streaming client and doesn't stream/host any content by itself. Images/Logo are copyright of their respective owners.\n" +
                        "        If you want to add/update/remove your radio station, Please inform us(cherrydigital.care@gmail.com).\n" +
                        "        \n\n\n" +
                        "        To add new sites or rss feed please contact me, Go to developer page on this app. Thank you.")
                .setPositiveButton("OK", (dialogInterface, i) -> {

                });

        return builder.create();
    }
}