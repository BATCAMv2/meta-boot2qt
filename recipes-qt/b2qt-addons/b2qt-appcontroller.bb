##############################################################################
##
## Copyright (C) 2017 The Qt Company Ltd.
## Contact: http://www.qt.io/licensing/
##
## This file is part of the Boot to Qt meta layer.
##
## $QT_BEGIN_LICENSE:COMM$
##
## Commercial License Usage
## Licensees holding valid commercial Qt licenses may use this file in
## accordance with the commercial license agreement provided with the
## Software or, alternatively, in accordance with the terms contained in
## a written agreement between you and The Qt Company. For licensing terms
## and conditions see http://www.qt.io/terms-conditions. For further
## information use the contact form at http://www.qt.io/contact-us.
##
## $QT_END_LICENSE$
##
##############################################################################

DESCRIPTION = "Boot to Qt Appcontroller"
LICENSE = "QtEnterprise"
LIC_FILES_CHKSUM = "file://main.cpp;md5=f25c7436dbc72d4719a5684b28dbcf4b;beginline=1;endline=17"

inherit qmake5
require recipes-qt/qt5/qt5-git.inc

SRC_URI = " \
    git://codereview.qt-project.org/qt-apps/boot2qt-appcontroller;branch=${QT_MODULE_BRANCH};protocol=http \
    file://appcontroller.conf \
    "

SRCREV = "384775293c03db5d25b57f112eaa1a01e380cbe6"

DEPENDS = "qtbase"

do_configure_append() {
    sed -i -e '/^platform=/d' ${WORKDIR}/appcontroller.conf
    echo platform=${MACHINE} >> ${WORKDIR}/appcontroller.conf
}

do_install_append() {
    install -m 0755 -d ${D}${sysconfdir}
    install -m 0755 ${WORKDIR}/appcontroller.conf ${D}${sysconfdir}/
}

FILES_${PN} += "${sysconfdir}/appcontroller.conf"
