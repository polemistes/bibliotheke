# SPDX-License-Identifier: GPL-3.0-or-later
"""Structure spike (2026-10-31): CirrusSearch totalhits for quality-related queries.

Evidence: docs/structure/search-counts.txt. Edit the list below and rerun."""
import sys
import wmhttp
queries = [
 ("de.wikisource.org", 0, "hastemplate:Textdaten"),
 ("de.wikisource.org", 0, 'hastemplate:Textdaten insource:"BEARBEITUNGSSTAND fertig"'),
 ("de.wikisource.org", 0, 'hastemplate:Textdaten insource:"BEARBEITUNGSSTAND korrigiert"'),
 ("de.wikisource.org", 0, 'hastemplate:Textdaten insource:"BEARBEITUNGSSTAND unkorrigiert"'),
 ("de.wikisource.org", 0, 'hastemplate:Textdaten insource:"BEARBEITUNGSSTAND teilkorrigiert"'),
 ("de.wikisource.org", 0, 'hastemplate:Textdaten insource:"BEARBEITUNGSSTAND unvollständig"'),
 ("de.wikisource.org", 0, 'hastemplate:Textdaten insource:"pages index"'),
 ("en.wikisource.org", 0, "hastemplate:header"),
 ("en.wikisource.org", 0, 'insource:"pages index"'),
 ("en.wikisource.org", 0, 'hastemplate:header -insource:"pages index"'),
 ("en.wikisource.org", 0, "hastemplate:incomplete"),
 ("en.wikisource.org", 0, "hastemplate:TextQuality"),
 ("en.wikisource.org", 0, "hastemplate:Textinfo"),
 ("en.wikisource.org", 0, "hastemplate:versions"),
 ("fr.wikisource.org", 0, "hastemplate:TextQuality"),
 ("fr.wikisource.org", 0, 'insource:"pages index"'),
 ("fr.wikisource.org", 0, 'hastemplate:TextQuality insource:"TextQuality 100"'),
 ("fr.wikisource.org", 0, 'hastemplate:TextQuality insource:"TextQuality 75"'),
 ("fr.wikisource.org", 0, 'hastemplate:TextQuality insource:"TextQuality 50"'),
 ("fr.wikisource.org", 0, 'hastemplate:TextQuality insource:"TextQuality 25"'),
 ("fr.wikisource.org", 0, 'hastemplate:TextQuality insource:"TextQuality Textes validés"'),
 ("no.wikisource.org", 0, 'insource:"pages index"'),
 ("no.wikisource.org", 0, '-insource:"pages index" insource:"Kilde"'),
]
for domain, ns, q in queries:
    try:
        d = wmhttp.action_api(domain, action="query", list="search", srsearch=q, srnamespace=ns,
                              srlimit=1, srinfo="totalhits", srprop="size")
        print(f"{domain:20} ns{ns} {d['query']['searchinfo'].get('totalhits', '?'):>8}  {q}")
    except Exception as e:
        print(f"{domain:20} ns{ns}   ERROR  {q}  {str(e)[:120]}")
