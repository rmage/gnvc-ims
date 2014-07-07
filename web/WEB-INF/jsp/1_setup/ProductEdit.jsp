<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS - Update Product</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%            
            //java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            //com.app.wms.engine.db.dto.Product dto = (com.app.wms.engine.db.dto.Product) m.get("dto");
            //String mode = (String) m.get("mode");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Product.htm" method="post">
                        <input type="hidden" name="mode" value="<%//=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="productId" value="${model.mode.productId}"/>
                        <table class="collapse tblForm row-select">
                            <caption>Product - Update</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td>Bar Code</td>
                                    <td>
                                        <label>
                                            <input type="text" id="autofocus" name="barcode" value="${model.mode.barCode}" size="30" maxlength="30"/>
                                        </label>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Item Code</td>
                                    <td>
                                        <label>
                                            <input type="text" name="productCode" readonly value="${model.mode.productCode}" size="30" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                    <td>Item Name</td>
                                    <td>
                                        <label>
                                            <input type="text" name="productName" value="${model.mode.productName}" size="30" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Brand Name</td>
                                    <td>
                                        <label>
                                            <input type="text" id="brand" name="brand" value="${model.mode.brandName}" size="30" />
                                        </label>
                                    </td>
                                    <td>Description</td>
                                    <td>
                                        <label>
                                            <input type="text" name="description" value="${model.mode.productDescription}" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Category</td>
                                    <td>
                                        <label>
                                            <input type="text" name="category" value="${model.mode.productCategory}" size="30"/>
                                        </label>
                                        <label>*</label>
                                    </td>
                                    <td>Item Color</td>
                                    <td>
                                        <label>
                                            <input type="text" name="color" value="${model.mode.productColor}" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Unit of Measurement (UoM)</td>
                                    <td>
                                        <label>
                                            <input type="text" name="color" value="${model.mode.uom}"/>
                                        </label>
                                        <label>*</label>
                                    </td>
                                    <td><b>Upload</b></td>
                                    <td>
                                        <a href="javascript:void(0)" id="uploadlink"> Upload Products</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Supplier Name</td>
                                    <td></td>
                                    <td>Buyer</td>
                                    <td>
                                        <label>
                                            <input type="text" id="buyer" name="buyer" value="${model.mode.buyer}" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Pack Style</td>
                                    <td>
                                        <label>
                                            <input type="text" id="packstyle" name="packstyle" value="${model.mode.packstyle}" size="30" />
                                        </label>
                                    </td>
                                    <td>Pack Size</td>
                                    <td>
                                        <select name="packsize">
                                            <option value="" <c:if test="${'' == model.mode.packsize}">selected="true"</c:if>></option>
                                            <option value="603" <c:if test="${'603' == model.mode.packsize}">selected="true"</c:if>>603</option>
                                            <option value="307" <c:if test="${'307' == model.mode.packsize}">selected="true"</c:if>>307</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>LID</td>
                                        <td>
                                            <label>
                                                <input type="text" id="lid" name="lid" value="${model.mode.lid}" size="30" />
                                        </label>
                                    </td>
                                    <td>NW / DW-PW</td>
                                    <td>
                                        <label>
                                            <input type="text" id="nwdwpw" name="nwdwpw" value="${model.mode.nwdwpw}" size="30" />
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                            <td colspan="5">
                                <span>
                                    <label>
                                        <input type="submit" name="btnSave" id="btnSave" value="Save" />
                                    </label>
                                    <input type="button" name="button" id="btnBack" value="Back" />
                                </span>
                            </td>
                            </tfoot>
                        </table>
                    </form>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'Product.htm';
                });
            });
        </script>

        <script language="JavaScript">
            function cek() {
                if (form.length.value == "" || form.width.value == "" || form.height.value == "") {
                    alert("data empty");
                    exit;
                }
            }
            function kali() {
                cek();
                a = eval(form.length.value);
                b = eval(form.width.value);
                c = eval(form.height.value);
                d = a * b * c
                form.volumeMatrix.value = d;
            }
        </script>

        <script type="text/javascript">
            function formfocus() {
                document.getElementById('autofocus').focus();
            }
            window.onload = formfocus;

        </script>
    </body>
</html>
