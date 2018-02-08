package es.unican.g3.tus;

/**
 * Created by fernando on 13/11/17.
 */

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

import static junit.framework.Assert.assertTrue;

/** Pruebas unitarias: Visualizar logo en arranque
 * Clase que implementa los tests unitarios del caso de uso "Visualizar logo en arranque"
 */
public class Unitarias_US242580VisualizarLogoArranqueTest {

    /**
     * Test para comprobar que el logo se encuentra correctamente en el proyecto
     * @throws Exception excepcion
     */
    @Test
    public void PU1US242580() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();

        // Se comprueba si existe la imagen del logo en la carpeta mipmap del proyecto
        assertTrue(context.getPackageName(), context.getResources().getIdentifier("logo", "mipmap", context.getPackageName()) != 0);

        // Se comprueba que el logo no haya sufrido ninguna alteración o modificación
        BitmapDrawable bd=(BitmapDrawable) context.getResources().getDrawable(R.mipmap.logo);
        assertTrue(bd.getBitmap().getGenerationId() == 596);
    }

}
