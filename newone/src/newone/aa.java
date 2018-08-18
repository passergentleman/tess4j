package newone;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileDescriptor;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.lang.RuntimeException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;


public class aa
{
	public static void main(String[] args) throws IOException
	{
		File root1 = new File("D:\\new\\tmall2");//δ����ͼƬ�ļ���λ��
        File[] files1 = root1.listFiles();
        int count1=0;
		for(File f : files1)//ȥˮӡ
		{
			count1++;
		BufferedImage img = ImageIO.read(f);
		for(int x=0;x<img.getWidth();x++)
		{
			for(int y=0;y<img.getHeight();y++)
			{
				int rgb = img.getRGB(x, y);
				Color color = new Color(rgb);
				int r=color.getRed();
				int g=color.getGreen();
				int b=color.getBlue();
				int c=255;
				if(r==0&&g==0&&b==0)
				{
					Color newColor = new Color(c,c,c);
					img.setRGB(x,y,newColor.getRGB());
				}
			}
		}
		int newwidth=(img.getWidth())/2;
		int newheight=(img.getHeight())/4;
		BufferedImage newimg=img.getSubimage(0, 0, newwidth, newheight);
		float resizeTimes=2.0f;
		int width2=(int)(newwidth*resizeTimes);
		int height2=(int)(newheight*resizeTimes);
		BufferedImage result=new BufferedImage(width2,height2,BufferedImage.TYPE_INT_RGB);
		result.getGraphics().drawImage(newimg.getScaledInstance(width2, height2, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
		String path="D:\\new\\tmall\\4.png";
		StringBuilder strb=new StringBuilder(path);
		strb.replace(13, 14, Integer.toString(count1));
		path=strb.toString();
		ImageIO.write(result, "png", new File(path));//���洦����ͼƬ
		}
		//����excel��
		HSSFWorkbook wkb=new HSSFWorkbook();
		HSSFSheet sheet = wkb.createSheet("��ҵ��");
		HSSFRow row1=sheet.createRow(0); 
		row1.createCell(0).setCellValue("��ҵ����");
	    row1.createCell(3).setCellValue("��ҵע���");   
	    sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
	    sheet.addMergedRegion(new CellRangeAddress(0,0,3,5));
		try {
            
            ITesseract instance = new Tesseract();
            instance.setDatapath("F:\\MyDownloads\\Tess4J");//tessdata�ļ�������λ��
            File root = new File("D:\\new\\tmall");//�����ͼƬ�ļ���λ��
            File[] files = root.listFiles();
            int count=0;
           for(File file : files)
            {
        	   	count++;
        	   	HSSFRow newrow=sheet.createRow(count);
        	   	newrow.createCell(0);
            	newrow.createCell(3);
            	sheet.addMergedRegion(new CellRangeAddress(count,count,0,2));
				sheet.addMergedRegion(new CellRangeAddress(count,count,3,5));
        	   	instance.setLanguage("eng");//Ӣ��ʶ��ע���
        	   	String numresult=instance.doOCR(file);
        	   	
        	   	instance.setLanguage("chi_sim");
            	String result = instance.doOCR(file);//����ʶ������
            	char[] cnum=numresult.toCharArray();
            	int n=0;
            	int m=0;
            	String num=new String();
            	for(int j=0;j<cnum.length;j++)//��ȡע���ѭ��
            	{
            		if(cnum[j]==':')
            			m++;
            		else if(m==1)
            		{
            			if((cnum[j]<='9'&&cnum[j]>='0')||(cnum[j]>='A'&&cnum[j]<='Z'))
            			{
            				num+=cnum[j];
            				n++;
            			}
            			if(n==18)
            			{
            				newrow.getCell(3).setCellValue(num); 
            				break;
            			}
            		}
            		else if(m>1) break;
            	}
            	char[] c=result.toCharArray();
            	int a=0;
            	String chi=new String();
            	for(int i=0;i< c.length;++i)//��ȡ����ѭ��
            	{
            		if(c[i]==':')
            			++a;
            		else
            		{
            				if(a==2)
            				{
            					if(c[i] == '˾')
            					{
            						chi += '˾';
            						newrow.getCell(0).setCellValue(chi);
            						break;
            					}
            					else chi += c[i];
            				}
            				else if(a>=2) break;
            		}
            	}
            	//�½�excel���
                FileOutputStream output=new FileOutputStream("D:\\new\\result.xls");
                try {
                wkb.write(output);
                output.flush();
                wkb.close();
                } catch(IOException e)
                {}
                result=null;
                num=null;
                chi=null;
    		}
        } catch (TesseractException e) {
            e.printStackTrace();
        }
	}
}
