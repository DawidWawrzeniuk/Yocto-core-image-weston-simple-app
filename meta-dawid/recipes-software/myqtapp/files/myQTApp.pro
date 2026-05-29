SUMMARY = "Qt6 Hello World"
LICENSE = "CLOSED"

DEPENDS += "qtbase qtwayland"

SRC_URI = "file://myQTApp.cpp \
           file://CMakeLists.txt \
"

S = "${WORKDIR}"

inherit cmake

do_install:append() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/myQTApp ${D}${bindir}/
}

FILES:${PN} += "${bindir}/myQTApp"
