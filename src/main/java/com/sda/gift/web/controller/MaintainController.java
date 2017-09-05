package com.sda.gift.web.controller;

import com.sda.gift.model.dto.OrderDto;
import com.sda.gift.model.entity.OrderEntity;
import com.sda.gift.model.entity.ProductEntity;
import com.sda.gift.framework.common.RestResult;
import com.sda.gift.service.OrderService;
import com.sda.gift.service.ProductService;
import com.sun.deploy.net.HttpResponse;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/24.
 */
@Controller
@RequestMapping("/maintain")
public class MaintainController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ModelAndView maintain(){
        ModelAndView mv = new ModelAndView("maintain");
        List<ProductEntity> productList = productService.getAll();
        Map<String,ProductEntity> productMap = new HashMap<>();
        for (ProductEntity product:productList) {
            productMap.put(product.getGuid(),product);
        }
        mv.addObject("productList",productList);
        mv.addObject("productMap",productMap);
        return mv;
    }

    @PostMapping("/addProduct")
    @ResponseBody
    public RestResult addProduct(ProductEntity productEntity){
        productService.add(productEntity);
        return new RestResult(true,"添加产品成功",null,null);
    }

    @PostMapping("/saveProduct")
    @ResponseBody
    public RestResult saveProduct(ProductEntity productEntity){
        productService.save(productEntity);
        return new RestResult(true,"更新成功",null,null);
    }

    public void createExcel(HttpServletResponse response){
        List<OrderDto> list= orderService.getAll();//此为我要导出的数据，这里写你的要导出数据list
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("股份工会2017“中秋”慰问品领取统计表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        //下面样式可作为导出左右分栏的表格模板
        sheet.setColumnWidth((short) 0, (short) 2400);// 设置列宽
        sheet.setColumnWidth((short) 1, (short) 2400);
        sheet.setColumnWidth((short) 2, (short) 4300);
        sheet.setColumnWidth((short) 3, (short) 4300);
        sheet.setColumnWidth((short) 4, (short) 4300);
        sheet.setColumnWidth((short) 5, (short) 2300);
        sheet.setColumnWidth((short) 6, (short) 2300);
        sheet.setColumnWidth((short) 7, (short) 2300);
        sheet.setColumnWidth((short) 8, (short) 2300);
        sheet.setColumnWidth((short) 9, (short) 4300);
        sheet.setColumnWidth((short) 10, (short) 4300);
        sheet.setColumnWidth((short) 11, (short) 4300);
        sheet.setColumnWidth((short) 12, (short) 4300);
        sheet.setColumnWidth((short) 13, (short) 4300);
        sheet.setColumnWidth((short) 14, (short) 4300);
        HSSFRow row = sheet.createRow((int) 0);
        row.setHeightInPoints(20);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font=wb.createFont();
        font.setColor(HSSFColor.RED.index);//HSSFColor.VIOLET.index //字体颜色
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);//居左
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("员工编号");
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell = row.createCell((short) 2);
        cell.setCellValue("身份证号");
        cell = row.createCell((short) 3);
        cell.setCellValue("所在部门");
        cell = row.createCell((short) 4);
        cell.setCellValue("所属单位");
        cell = row.createCell((short) 5);
        cell.setCellValue("A数量");
        cell = row.createCell((short) 6);
        cell.setCellValue("B数量");
        cell = row.createCell((short) 7);
        cell.setCellValue("C数量");
        cell = row.createCell((short) 8);
        cell.setCellValue("D数量");
        cell = row.createCell((short) 9);
        cell.setCellValue("E数量");
        cell = row.createCell((short) 10);
        cell.setCellValue("F数量");
        cell = row.createCell((short) 11);
        cell.setCellValue("G数量");
        cell = row.createCell((short) 12);
        cell.setCellValue("领取地点");
        cell = row.createCell((short) 13);
        cell.setCellValue("领取时间");
        cell = row.createCell((short) 14);
        cell.setCellValue("物品总价");
        for(int i=0;i<cell.getColumnIndex()+1;i++){//第一行每一个单元给样式
            cell.getRow().getCell(i).setCellStyle(style);
        }
        // 第五步，写入到excel
        for(int i=0;i<list.size();i++){
            style = wb.createCellStyle();
            row = sheet.createRow((int) i + 1);
            row.setHeightInPoints(20);
            // 第四步，创建单元格，并设置值
//            row.createCell((short) 0).setCellValue(list.get(i).getId()+"");
//            row.createCell((short) 1).setCellValue(list.get(i).getTripId()+"");
//            if(list.get(i).getApplyType().equals(0)){
//                row.createCell((short) 2).setCellValue("人肉买手");
//            }else{
//                row.createCell((short) 2).setCellValue("本土买手");
//            }
//
//            row.createCell((short) 3).setCellValue(list.get(i).getBuyerId()+"");
//            row.createCell((short) 4).setCellValue(list.get(i).getFromCity());
//            row.createCell((short) 5).setCellValue(list.get(i).getMobile());
//            row.createCell((short) 6).setCellValue(list.get(i).getRealname());
//            if(list.get(i).getTripStime()!=null){
//                String dateString=DateUtil.getDateStrFromDate(list.get(i).getTripStime());
//                row.createCell((short) 7).setCellValue(dateString);
//            }else{
//                row.createCell((short) 7).setCellValue("");
//            }
//            if(list.get(i).getTripEtime()!=null){
//                String dateString=DateUtil.getDateStrFromDate(list.get(i).getTripEtime());
//                row.createCell((short) 8).setCellValue(dateString);
//            }else{
//                row.createCell((short) 8).setCellValue("");
//            }
//            row.createCell((short) 9).setCellValue(list.get(i).getWxSignal());
//            if(list.get(i).getCreateTime()!=null){
//                String dateString=DateUtil.getLongStrFromDate(list.get(i).getCreateTime());
//                row.createCell((short) 10).setCellValue(dateString);
//            }else{
//                row.createCell((short) 10).setCellValue("");
//            }

        }
        // 第六步，将文件存到指定位置
        try
        {   //输出Excel文件
            OutputStream output=response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=apply.xls");
            response.setContentType("application/msexcel");
            wb.write(output);
            output.close();
            wb.close();
        }catch (Exception e)  {
            e.printStackTrace();
        }
    }

}
