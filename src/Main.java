import java.io.IOException;
import java.io.File;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDNonTerminalField;

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
        System.out.println(fields.size());
        for(PDField field : fields)
        {
            for(PDField child : ((PDNonTerminalField)field).getChildren())
            {
                for(PDField grandchild : ((PDNonTerminalField)child).getChildren())
                {
                    System.out.println(grandchild.getPartialName());
                }
                System.out.println(child.getPartialName());
            }
            System.out.println(field.getPartialName());
        }
        document.close();

    }
}
