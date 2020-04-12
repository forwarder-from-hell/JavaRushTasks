package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

public class ImageReaderFactory {
    public static ImageReader getImageReader(ImageTypes imageType) {
        if(imageType == null)
            throw new IllegalArgumentException("Неизвестный тип картинки");

        switch (imageType) {
            case JPG:
                return new JpgReader();
            case PNG:
                return new PngReader();
            case BMP:
                return new BmpReader();
            default:
                throw new IllegalArgumentException("Неизвестный тип картинки");
        }
    }
}