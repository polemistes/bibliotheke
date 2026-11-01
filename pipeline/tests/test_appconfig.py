# SPDX-License-Identifier: GPL-3.0-or-later
"""The app's wiki-profiles.json is exported from pipeline/config (WP3)."""

from wscatalog import appconfig


def test_app_profiles_up_to_date():
    assert appconfig.main(["--check"]) == 0, "run: python -I -m wscatalog.appconfig"
