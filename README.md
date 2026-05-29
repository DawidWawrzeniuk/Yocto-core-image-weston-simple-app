# Yocto Build for Raspberry Pi 5

This repository contains instructions for building a custom Linux image for Raspberry Pi 5 using the Yocto Project.

---

# Requirements

Tested on:

* Ubuntu 22.04 LTS
* Raspberry Pi 5
* Yocto Scarthgap release

Install required packages:

```bash
sudo apt update

sudo apt install gawk wget git diffstat unzip texinfo gcc build-essential \
chrpath socat cpio python3 python3-pip python3-pexpect xz-utils \
debianutils iputils-ping python3-git python3-jinja2 \
libegl1-mesa libsdl1.2-dev pylint xterm zstd liblz4-tool
```

---

# 1. Clone Yocto (Poky)

Clone the Yocto Project repository exactly as shown below:

```bash
git clone -b scarthgap git://git.yoctoproject.org/poky poky-rpi
```

Enter the project directory:

```bash
cd poky-rpi
```

---

# 2. Clone Raspberry Pi Layer

Download the Raspberry Pi BSP layer:

```bash
git clone -b scarthgap https://github.com/agherzan/meta-raspberrypi.git
```

---

# 3. Initialize Build Environment

Run the Yocto environment setup script:

```bash
source oe-init-build-env
```

This command creates the `build/` directory automatically.

---

# 4. Configure Raspberry Pi 5

Edit the configuration file:

```bash
nano conf/local.conf
```

Set the target machine:

```conf
MACHINE = "raspberrypi5"
INIT_MANAGER = "systemd"
LICENSE_FLAGS_ACCEPTED = "synaptics-killswitch"
```

---

# 5. Add meta-raspberrypi Layer

Edit:

```bash
nano conf/bblayers.conf
```

Add the Raspberry Pi layer:

```conf
${TOPDIR}/../meta-raspberrypi \
```

Example configuration:

```conf
BBLAYERS ?= " \
  /path/to/poky-rpi/meta \
  /path/to/poky-rpi/meta-poky \
  /path/to/poky-rpi/meta-yocto-bsp \
  /path/to/poky-rpi/meta-raspberrypi \
"
```

---

# 6. Build the Image

Build the minimal Linux image:

```bash
bitbake core-image-weston
```

The build process may take a long time depending on your hardware.

---

# 7. Generated Image Location

After a successful build, the image will be available in:

```bash
tmp/deploy/images/raspberrypi5/
```

Example output:

```bash
core-image-weston-raspberrypi5.rootfs.wic.bz2
```

---

# 8. Extract the Image

```bash
bunzip2 core-image-minimal-raspberrypi5.rootfs.wic.bz2
```

---

# 9. Flash Image to SD Card

Check available storage devices:

```bash
lsblk
```

Flash the image:

```bash
sudo dd if=core-image-minimal-raspberrypi5.rootfs.wic of=/dev/sdX bs=4M status=progress conv=fsync
```

Replace:

```bash
/dev/sdX
```

with your SD card device.

---

# 10. Boot Raspberry Pi 5

1. Insert the SD card into Raspberry Pi 5
2. Connect power
3. The system should boot automatically

---

# References

* Yocto Project: https://www.yoctoproject.org/
* meta-raspberrypi: https://github.com/agherzan/meta-raspberrypi
