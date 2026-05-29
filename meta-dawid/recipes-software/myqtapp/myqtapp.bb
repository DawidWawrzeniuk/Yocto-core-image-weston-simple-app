SUMMARY = "This is a test QT program"
DESCRIPTION = "This is a test QT program"
LICENSE = "CLOSED"

DEPENDS += "qtbase qtwayland"

SRC_URI = "file://CMakeLists.txt \
           file://myQTApp.cpp \
"

S = "${WORKDIR}"

inherit cmake qt6-cmake

do_install:append() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/myQTApp ${D}${bindir}/
}

FILES:${PN} += "${bindir}/myQTApp"
