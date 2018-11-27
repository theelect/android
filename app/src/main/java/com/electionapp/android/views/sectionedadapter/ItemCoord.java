package com.electionapp.android.views.sectionedadapter;

@SuppressWarnings("WeakerAccess")
public class ItemCoord {

  private final int section;
  private final int relativePos;

  ItemCoord(int section, int relativePos) {
    this.section = section;
    this.relativePos = relativePos;
  }

  public int section() {
    return section;
  }

  public int relativePos() {
    return relativePos;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof ItemCoord
        && ((ItemCoord) obj).section() == section()
        && ((ItemCoord) obj).relativePos() == relativePos();
  }

  @Override
  public String toString() {
    return section + ":" + relativePos;
  }

}
