SUMMARY = "This is a test C program"
DESCRIPTION = "This is a test C program"
SECTION = "examples"

LICENSE = "CLOSED"

SRC_URI = "file://mycapp.c"

S = "${WORKDIR}"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} mycapp.c -o mycapp
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 mycapp ${D}${bindir}/
}
