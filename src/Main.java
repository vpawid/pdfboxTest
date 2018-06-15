import java.io.IOException;
import java.io.File;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDNonTerminalField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;


import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;


public class Main {

    public static void main(String[] args) throws IOException {
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        System.out.println("Hello World!");
        File file = new File("C:/Users/niceg/Downloads/f1040.pdf");
        PDDocument document = PDDocument.load(file);
        System.out.println("PDF loaded");
        PDDocumentCatalog cat = document.getDocumentCatalog();
        PDAcroForm acroForm = cat.getAcroForm();
        List<PDField> fields = acroForm.getFields();
       // System.out.println(fields.size());
        for(PDField field : fields)
        {
            listing(field);

        }
        PDTextField fieldToFill = (PDTextField) acroForm.getField("topmostSubform[0].Page1[0].HeaderPg1[0].f1_01[0]");
        fieldToFill.setValue("testing123");
        document.save("test.pdf");
        document.close();

    }
    public static void listing(PDField field)
    {
        System.out.println(field.getFullyQualifiedName());
        System.out.println(field.getPartialName());
        if (field instanceof PDNonTerminalField)
        {
            PDNonTerminalField nonTerminalField = (PDNonTerminalField) field;
            for (PDField child : nonTerminalField.getChildren())
            {
                listing(child);
            }
        }
    }
}
