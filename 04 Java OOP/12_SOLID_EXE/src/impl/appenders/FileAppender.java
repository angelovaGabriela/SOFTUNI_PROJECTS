package impl.appenders;

import enums.ReportLevel;
import interfaces.File;
import interfaces.Layout;

public class FileAppender extends BaseAppender {
    private File file;
    public FileAppender(Layout layout) {
        super(layout);
        this.file = new LogFile();

    }

    public FileAppender(Layout layout, String fileName) {
      this(layout);
      this.file = new LogFile(fileName);

    }


    @Override
    public void append(String time, String message, ReportLevel reportLevel) {

        if (canAppend(reportLevel)) {
            String output = this.layout.format(time, message, reportLevel);
            increaseMessagesCount();
            file.append(output);
        }
    }



    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return super.toString() + ", File size: " + this.file.getSize();
    }
}
