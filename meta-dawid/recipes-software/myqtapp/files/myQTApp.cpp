#include <QApplication>
#include <QLabel>
#include <QWidget>

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);

    QLabel hello("<center>HELLO WORLD!</center>");
    hello.setWindowTitle("My first QT");
    hello.resize(100, 100);
    hello.show();

    return app.exec();
}
