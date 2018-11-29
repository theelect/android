package com.tonyecoleelection.android.views.sectionedadapter;

import android.support.annotation.RestrictTo;

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
interface ItemProvider {

  int getSectionCount();

  int getItemCount(int sectionIndex);

  boolean showHeadersForEmptySections();

  boolean showFooters();
}
