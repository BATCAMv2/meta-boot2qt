############################################################################
##
## Copyright (C) 2019 The Qt Company Ltd.
## Copyright (C) 2019 Pelagicore AG.
## Contact: https://www.qt.io/licensing/
##
## This file is part of the Boot to Qt meta layer.
##
## $QT_BEGIN_LICENSE:GPL$
## Commercial License Usage
## Licensees holding valid commercial Qt licenses may use this file in
## accordance with the commercial license agreement provided with the
## Software or, alternatively, in accordance with the terms contained in
## a written agreement between you and The Qt Company. For licensing terms
## and conditions see https://www.qt.io/terms-conditions. For further
## information use the contact form at https://www.qt.io/contact-us.
##
## GNU General Public License Usage
## Alternatively, this file may be used under the terms of the GNU
## General Public License version 3 or (at your option) any later version
## approved by the KDE Free Qt Foundation. The licenses are as published by
## the Free Software Foundation and appearing in the file LICENSE.GPL3
## included in the packaging of this file. Please review the following
## information to ensure the GNU General Public License requirements will
## be met: https://www.gnu.org/licenses/gpl-3.0.html.
##
## $QT_END_LICENSE$
##
############################################################################

DESCRIPTION = "Neptune 3 IVI UI"
LICENSE = "BitstreamVera & ( GPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = "\
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://imports_shared/assets/fonts/LICENSE;md5=b5c5273ad988fb6b52bcb7b5a2a1f370 \
"

inherit qt5-module systemd
require recipes-qt/qt5/qt5-git.inc

QT_GIT = "git://codereview.qt-project.org/${QT_GIT_PROJECT}"
QT_GIT_PROTOCOL = "http"
QT_GIT_PROJECT = "qt-apps"

SRC_URI += " \
    file://neptune.service \
    "

SRCREV = "a0a4afe3ac6f34763e864cf6b0ae7e5ba26e860b"

QMAKE_PROFILES = "${S}/neptune3-ui.pro"

DEPENDS = "\
    qtbase \
    qtdeclarative \
    qttools-native \
    qtquickcontrols2 \
    qtapplicationmanager \
    qtivi qtivi-native \
    qtremoteobjects qtremoteobjects-native \
    "
RDEPENDS_${PN} = "\
    dbus \
    otf-noto ttf-opensans \
    qtapplicationmanager qtapplicationmanager-tools \
    qtvirtualkeyboard \
    qtquickcontrols2-qmlplugins \
    qtgraphicaleffects-qmlplugins \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtwebengine', '', d)} \
    "

do_install_append() {
    install -m 0755 -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/neptune.service ${D}${systemd_unitdir}/system/

    # Move the fonts to the system-wide font location
    install -m 0755 -d ${D}${datadir}/fonts/ttf/
    mv ${D}/opt/neptune3/imports_shared/assets/fonts/*.ttf ${D}${datadir}/fonts/ttf/
    rm -rf ${D}/opt/neptune3/imports_shared/assets/fonts/

    # Don't package tests
    rm -rf ${D}/usr/share/tests
}

PACKAGES =+ "${PN}-apps"
RRECOMMENDS_${PN} += "${PN}-apps"

FILES_${PN}-apps += "/opt/neptune3/apps"
FILES_${PN} += "\
    /opt/neptune3 \
    ${datadir}/fonts/ttf \
    "
FILES_${PN}-dev += "\
    /opt/neptune3/lib/*.so \
    "

SYSTEMD_SERVICE_${PN} = "neptune.service"
