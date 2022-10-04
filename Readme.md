CSVProcessor

Created by: Chris Hilgenberg

Email Address: chris.g.hilgenberg@gmail.com

Purpose:
CSVProcessor takes a CSV file that has the following information in it:

UserId, Name, Version, InsuranceCompanyName

And sorts the information and groups by InsuranceCompanyName and Name (i.e. Named Insured)

It then writes out the file with the sorted information for later processing (this is to mimic an ordered process for EDI processing)

Uses:
Apache Commons CSV

Issues Encountered:
1. CSVPrinter.printrecord() did not write to file as expected from its API documents (and examples online), so I had to replace it with a standard FileWriter
2. I ran out of time to implement the requirement "If UserId is identical for an InsuranceCompany, grab only the record with the highest version number". I'm sure with a bit more time I'll work on creating a solution for that.
3. I wanted to have both a manual and a quartz scheduler version of this, but will have to add later after already being submitted. 

Testing:

In application.properties, two paths are setup (and are of least resistance to file permissions):

File that is read from: C:\Users\Public\Documents\CSVtoProcess.csv (this must exist for app before calling)
Folder where processed files go: C\Users\Public\Documents\Processed

Process is triggered locally from localhost:8080/csv/conversion