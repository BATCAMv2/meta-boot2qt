##############################################################################
##
## Copyright (C) 2016 The Qt Company Ltd.
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

DESCRIPTION = "Boot to Qt Wayland Demo Compositor"
LICENSE = "QtEnterprise"
LIC_FILES_CHKSUM = "file://main.cpp;md5=56e7f80d726792929d5d089852de7cde;beginline=1;endline=39"

inherit qmake5
require recipes-qt/qt5/qt5-git.inc

QT_GIT = "git://github.com/qtproject"
QT_MODULE = "qt-apps-boot2qt-demos"

SRCREV = "b6217fb2bb7702324da2a9f3f45be4a60e6ffe6c"

S = "${WORKDIR}/git/wayland/democompositor/"

DEPENDS = "qtbase qtwayland"
RDEPENDS_${PN} = "qtwayland (>= 5.7)"

do_install_append() {
    install -d -m0775 ${D}/usr/bin
    install -m0775 ${B}/democompositor ${D}/usr/bin
}
