/*
 * Copyright (C) 2011 Whisper Systems
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.thoughtcrime.securesms;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity container for selecting a list of contacts.
 *
 * @author Moxie Marlinspike
 *
 */
public class ContactMultiSelectionActivity extends ContactSelectionActivity {

  @SuppressWarnings("unused")
  private final static String TAG = ContactMultiSelectionActivity.class.getSimpleName();
  private View addMembers;

  @Override
  protected void onCreate(Bundle icicle, boolean ready) {
    getIntent().putExtra(ContactSelectionListFragment.MULTI_SELECT, true);
    super.onCreate(icicle, ready);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

    // it's a bit confusing having one "X" button on the left and one on the right -
    // and the "clear search" button is not that important.
    getToolbar().setUseClearButton(false);
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    MenuInflater inflater = this.getMenuInflater();
    menu.clear();

    inflater.inflate(R.menu.add_members, menu);
    addMembers = findViewById(R.id.menu_add_members);
    super.onPrepareOptionsMenu(menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);
    switch (item.getItemId()) {
      case R.id.menu_add_members:
        saveSelection();
        finish();
        return true;
      case android.R.id.home:
        finish();
        return true;
    }

    return false;
  }

  @Override
  public void onBackPressed() {
    saveSelection();
    super.onBackPressed();
  }

  private void saveSelection() {
    Intent resultIntent = getIntent();
    List<String> selectedContacts = contactsFragment.getSelectedContacts();

    if (selectedContacts != null) {
      resultIntent.putStringArrayListExtra("contacts", new ArrayList<>(selectedContacts));
    }

    setResult(RESULT_OK, resultIntent);
  }

  @Override
  public void onFilterChanged(String filter) {
    super.onFilterChanged(filter);
    if (addMembers != null) {
      if (filter == null || "".equals(filter)) {
        addMembers.setVisibility(View.GONE);
      } else {
        addMembers.setVisibility(View.VISIBLE);
      }
    }
  }
}
