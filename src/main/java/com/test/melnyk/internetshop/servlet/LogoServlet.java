package com.test.melnyk.internetshop.servlet;

import com.test.melnyk.internetshop.model.User;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import static com.test.melnyk.internetshop.consts.Path.LOGO_DIRECTORY;

@WebServlet("/logo")
public class LogoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        File file = new File(LOGO_DIRECTORY + user.getLogin() + ".jpg");
        BufferedImage bufferedImage = null;
        if (!file.exists()) {
            bufferedImage = ImageIO.read(new File("C:/logo/example.png"));
        } else {
            bufferedImage = ImageIO.read(file);
        }
        OutputStream outputStream = resp.getOutputStream();
        ImageIO.write(bufferedImage, "jpg", outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
