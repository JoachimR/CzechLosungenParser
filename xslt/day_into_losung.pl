#! /usr/bin/perl -w

$name='';

while (<>) {
    if(/^<DAY .* type="(sunday|holiday)" name="(.*)"/) {
	$name = $2;
	$name =~ s/".*//;
	#print "$name\n";
    }
    print;
    if (/^<LOSUNG /) {
	print $name ? " <Sonntag>$name</Sonntag>\n" : " <Sonntag/>\n";
	$name = '';
    }
}
