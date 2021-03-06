############################################################################
##
## Copyright (C) 2018 The Qt Company Ltd.
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

FILESEXTRAPATHS_append := "${THISDIR}/${PN}:"
SRC_URI += "file://0001-Disable-more-warnings.patch"

CROSS_COMPILE_PREFIX = "${CROSS_COMPILE}"

RDEPENDS_${KERNEL_PACKAGE_NAME}-base = "${KERNEL_PACKAGE_NAME}-image ${KERNEL_PACKAGE_NAME}-devicetree"

python do_patch () {
    bb.build.exec_func('patch_do_patch', d)
}

do_unpack_append() {
    mkdir -p ${STAGING_KERNEL_DIR}
    rm -rf ${STAGING_KERNEL_DIR}
    ln -s ${S} ${STAGING_KERNEL_DIR}
}

do_install_append() {
    kernel_do_install
    install -m 0664 -t ${D}/boot/ ${B}/arch/arm64/boot/dts/*.dtb
}

dtbs_deploy() {
    install -m 0664 -t ${DEPLOY_DIR_IMAGE} ${B}/arch/arm64/boot/dts/*.dtb
}
