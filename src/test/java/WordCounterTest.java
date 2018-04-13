
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.WordCounter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class WordCounterTest {

    // Entries in the docx file
    private final static String TEST_DOC_TEST_CASE_TEXT_1 = "test_case_text_1";
    private final static String TEST_DOC_TEST_CASE_TEXT_2 = "test_case_text_2";
    private final static String TEST_DOC_TEST_CASE_TEXT_3 = "test_case_text_3";

    private final static String TEST_DOC_TEST_CASE_TABLE_1 = "test_case_table_1";
    private final static String TEST_DOC_TEST_CASE_TABLE_2 = "test_case_table_2";
    private final static String TEST_DOC_TEST_CASE_TABLE_3 = "test_case_table_3";

    private final static String TEST_DOC_TEST_CASE = "test_case";
    private final static String TEST_DOC_TEST_CASE_WORD = "word";
    private final static String TEST_DOC_TEST_CASE_RANDOM_WORD = "Uga-chaga";

    private final static String TEST_DOC_TEST_CASE_SAME_VALS_IN_SAME_RUN = "same_run_vals";
    private final static String TEST_DOC_TEST_CASE_SAME_VALS_IN_ONE_PARA = "same_vals_one_para";

    private static XWPFDocument doc;
    private static WordCounter wordCounter;

    @BeforeClass
    public static void setup() throws Exception{
        InputStream inputStream = new FileInputStream(new File("./src/test/resources/docxfile.docx"));
        doc = new XWPFDocument(inputStream);
        wordCounter = new WordCounter();
    }

    @Test
    public void wordCountInFile_shouldFindOneVal() throws Exception {
        assertEquals(1, wordCounter.countWordsInTable(doc, TEST_DOC_TEST_CASE_TABLE_1));
        assertEquals(1, wordCounter.countWordsInTable(doc, TEST_DOC_TEST_CASE_TABLE_3));

        assertEquals(1, wordCounter.countWordsInText(doc, TEST_DOC_TEST_CASE_TEXT_1));
        assertEquals(1, wordCounter.countWordsInText(doc, TEST_DOC_TEST_CASE_TEXT_3));
    }

    @Test
    public void wordCountInFile_shouldFind4Vals() throws Exception {
        assertEquals(4, wordCounter.countWordsInTable(doc, TEST_DOC_TEST_CASE));
        assertEquals(4, wordCounter.countWordsInText(doc, TEST_DOC_TEST_CASE));
    }

    @Test
    public void wordCountInFile_shouldNoVals() throws Exception {
        assertEquals(0, wordCounter.countWordsInTable(doc, TEST_DOC_TEST_CASE_TEXT_2)); // Scattered in different paragraphs
        assertEquals(0, wordCounter.countWordsInTable(doc, TEST_DOC_TEST_CASE_RANDOM_WORD));

        assertEquals(0, wordCounter.countWordsInText(doc, TEST_DOC_TEST_CASE_TABLE_2)); // Scattered in different paragraphs
        assertEquals(0, wordCounter.countWordsInText(doc, TEST_DOC_TEST_CASE_RANDOM_WORD));
    }

    // Edge case, not required yet
    /*@Test
    public void wordCountInFile_shouldFind2ValsInSameRun() throws Exception {
        assertEquals(2, wordCounter.findWordsInDocx(doc, TEST_DOC_TEST_CASE_SAME_VALS_IN_SAME_RUN));
    }*/

    @Test
    public void wordCountInFile_shouldFind2ValsInSameParaDiffRuns() throws Exception {
        assertEquals(2, wordCounter.countWordsInText(doc, TEST_DOC_TEST_CASE_SAME_VALS_IN_ONE_PARA));
    }



}