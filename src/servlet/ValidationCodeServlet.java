package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidationCodeServlet extends HttpServlet {

	public static final String CHARS="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final int DEFAULT_LENGTH = 4;
    public static final String MIME_TYPE = "image/png";
	public static final String IMG_FORMAT = "PNG";
	public static final String VALIDATION_CODE = "code";
	public static final int IMG_HEIGHT = 33;
	public static final int IMG_WIDTH = 100;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }


    /*实现的核心代码*/
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType(MIME_TYPE);
        HttpSession session=request.getSession();
        int width=IMG_WIDTH;
        int height=IMG_HEIGHT;
        
        //设置浏览器不要缓存此图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        
        //创建内存图像并获得图形上下文
        BufferedImage image=new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics g=image.getGraphics();
        
        /*
         * 产生随机验证码
         * 定义验证码的字符表
         */
        
        char[] rands=new char[4];
        for(int i=0;i<DEFAULT_LENGTH;i++){
            int rand=(int) (Math.random() *36);
            rands[i]=CHARS.charAt(rand);
        }
        
        /*
         * 产生图像
         * 画背景
         */
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        
        /*
         * 随机产生120个干扰点
         */
        
        for(int i=0;i<120;i++){
            int x=(int)(Math.random()*width);
            int y=(int)(Math.random()*height);
            int red=(int)(Math.random()*255);
            int green=(int)(Math.random()*255);
            int blue=(int)(Math.random()*255);
            g.setColor(new Color(red,green,blue));
            g.drawOval(x, y, 1, 0);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.ITALIC|Font.BOLD,20));
        
        //在不同高度输出验证码的不同字符
        g.drawString(""+rands[0], 6, 22);
        g.drawString(""+rands[1], 34, 24);
        g.drawString(""+rands[2], 50, 20);
        g.drawString(""+rands[3], 76, 23);
        g.dispose();
        
        //将图像传到客户端
        ServletOutputStream sos=response.getOutputStream();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(image, IMG_FORMAT, baos);
        byte[] buffer=baos.toByteArray();
        response.setContentLength(buffer.length);
        sos.write(buffer);
        baos.close();
        sos.close();
        
        session.setAttribute(VALIDATION_CODE, new String(rands));
    }

}