<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <%@include file="/WEB-INF/jspf/head.jspf" %>

    <body leftmargin="0" topmargin="0" bottommargin="0" rightmargin="0">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <%@include file="/WEB-INF/jspf/header.jspf" %>
            <tr>
                <td width="20%">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="5%">&nbsp;</td>
                            <td width="95%"><div align="center"><br />
                                    <img src="resources/img/logo.png" width="212" height="53" /><br />
            								IMS - Warehouse Management System<br />
                                    <br />
                                </div></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                                <div id="Accordion1" class="Accordion" tabindex="0">
                                    <div class="AccordionPanel">
                                        <div class="AccordionPanelTab"><div class="judul">Menu</div></div>
                                        <div class="AccordionPanelContent">
                                            <table width="90%" border="0" cellspacing="0" cellpadding="4">
                                                <tr>
                                                    <td colspan="2">Report Detail IMS</td>
                                                </tr>
                                                <tr>
                                                    <td>&#149;</td>
                                                    <td><a href="#" onclick="loadPage('ReportStockOnHand.htm')">Stock On Hand</a></td>
                                                </tr>
                                                <tr>
                                                    <td>&#149;</td>
                                                    <td><a href="#" onclick="loadPage('ReportExpiredDate.htm')">Expired Date</a></td>
                                                </tr>
                                                <tr>
                                                    <td>&#149;</td>
                                                    <td><a href="#" onclick="loadPage('ReportStatusByLocation.htm')">Status By Location</a></td>
                                                </tr>
                                                <tr>
                                                    <td>&#149;</td>
                                                    <td><a href="#" onclick="loadPage('ReportProductDamage.htm')">Product Damage</a></td>
                                                </tr>
                                                <tr>
                                                    <td>&#149;</td>
                                                    <td><a href="#" onclick="loadPage('ReportHistorical.htm')">Historycal</a></td>
                                                </tr>
                                                <tr>
                                                    <td>&#149;</td>
                                                    <td><a href="#" onclick="loadPage('ReportDeliveryOrder.htm')">History Delivery Order</a></td>
                                                </tr>
                                                <tr>
                                                    <td>&#149;</td>
                                                    <td><a href="#" onclick="loadPage('ReportPickList.htm')">Pick List</a></td>
                                                </tr>
                                                <tr>
                                                    <td>&#149;</td>
                                                    <td><a href="#" onclick="loadPage('ReportConsigne.htm')">History Consigne</a></td>
                                                </tr>
                                                <tr>
                                                    <td>&#149;</td>
                                                    <td><a href="#" onclick="loadPage('ReportReplenish.htm')">History Replenishment</a></td>
                                                </tr>
                                                

                                            </table><p>&nbsp;</p>
                                        </div>
                                    </div>
                                    <div class="AccordionPanel">
                                        <div class="AccordionPanelTab">
                                            <div class="judul">Reports</div></div>
                                        <div class="AccordionPanelContent">
                                            <table width="90%" border="0" cellspacing="0" cellpadding="4">
                                                <tr>
                                                    <td colspan="2">List of Reports</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><div align="center">PT SPFI Yasa Group © 2012 </div></td>
                        </tr>
                    </table>    </td>
                <td width="80%">
                    <iframe  id="myFrame" width="100%" height="800px" scrolling="no" frameborder="0">
                    </iframe></td>
            </tr>
        </table>
        <script type="text/javascript">
            <!--
            var Accordion1 = new Spry.Widget.Accordion("Accordion1");
            //-->
        </script>
    </body>
</html>