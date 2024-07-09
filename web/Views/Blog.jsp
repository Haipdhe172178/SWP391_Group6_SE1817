<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bookly - Bookstore eCommerce Website Template</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="author" content="">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap" rel="stylesheet">
</head>
<body>
    <head>
        <title>Bookly - Bookstore eCommerce Website Template</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap" rel="stylesheet">
        <style>
            .filter-box {
                border: 1px solid #ddd;
                padding: 20px;
                border-radius: 5px;
                background-color: white;
                margin-bottom: 20px;
                margin-top: 20px
            }

            .filter-box h2 {
                text-align: center;
                margin-top: 0;
                font-size: 20px;
            }
            .collapsible {
                cursor: pointer;
                user-select: none;
                background-color: #f86d72;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                margin-bottom: 10px;
                font-size: 14px;
                position: relative;
            }
            .collapsible::after {
                content: '\002B';
                font-size: 14px;
                position: absolute;
                right: 20px;
                color: #777;
            }
            .collapsible.active::after {
                content: '\2212';
            }
            .content {
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                margin-bottom: 10px;
            }
            .section-title h3 {
                margin: 0;
                font-size: 16px;
                color: #fff;
            }
            .product-categories, .product-tags {
                padding-left: 20px;
            }
            .cat-item, .tags-item {
                margin-bottom: 10px;
                font-size: 14px;
            }
            button[type="submit"] {
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                background-color: #f86d72;
                color: #fff;
                cursor: pointer;
            }
            button[type="submit"]:hover {
                background-color: #f86d72;
            }
        </style>
    </head>

    <body>
        <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="search" xmlns="http://www.w3.org/2000/symbolsvg" viewBox="0 0 24 24">
        <path fill="currentColor" fill-rule="evenodd" d="M11.5 2.75a8.75 8.75 0 1 0 0 17.5a8.75 8.75 0 0 0 0-17.5M1.25 11.5c0-5.66 4.59-10.25 10.25-10.25S21.75 5.84 21.75 11.5c0 2.56-.939 4.902-2.491 6.698l3.271 3.272a.75.75 0 1 1-1.06 1.06l-3.272-3.271A10.21 10.21 0 0 1 11.5 21.75c-5.66 0-10.25-4.59-10.25-10.25" clip-rule="evenodd" />
    </symbol>
    <symbol id="user" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
        <path fill="currentColor" fill-rule="evenodd" d="M12 1.25a4.75 4.75 0 1 0 0 9.5a4.75 4.75 0 0 0 0-9.5M8.75 6a3.25 3.25 0 1 1 6.5 0a3.25 3.25 0 0 1-6.5 0M12 12.25c-2.313 0-4.445.526-6.024 1.414C4.42 14.54 3.25 15.866 3.25 17.5v.102c-.001 1.162-.002 2.62 1.277 3.662c.629.512 1.51.877 2.7 1.117c1.192.242 2.747.369 4.773.369s3.58-.127 4.774-.369c1.19-.24 2.07-.605 2.7-1.117c1.279-1.042 1.277-2.5 1.276-3.662V17.5c0-1.634-1.17-2.96-2.725-3.836c-1.58-.888-3.711-1.414-6.025-1.414M4.75 17.5c0-.851.622-1.775 1.961-2.528c1.316-.74 3.184-1.222 5.29-1.222c2.104 0 3.972.482 5.288 1.222c1.34.753 1.961 1.677 1.961 2.528c0 1.308-.04 2.044-.724 2.6c-.37.302-.99.597-2.05.811c-1.057.214-2.502.339-4.476.339c-1.974 0-3.42-.125-4.476-.339c-1.06-.214-1.68-.509-2.05-.81c-.684-.557-.724-1.293-.724-2.601" clip-rule="evenodd" />
    </symbol>
    <symbol id="heart" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
        <path fill="currentColor" fill-rule="evenodd" d="M5.624 4.424C3.965 5.182 2.75 6.986 2.75 9.137c0 2.197.9 3.891 2.188 5.343c1.063 1.196 2.349 2.188 3.603 3.154c.298.23.594.459.885.688c.526.415.995.778 1.448 1.043c.452.264.816.385 1.126.385c.31 0 .674-.12 1.126-.385c.453-.265.922-.628 1.448-1.043c.29-.23.587-.458.885-.687c1.254-.968 2.54-1.959 3.603-3.155c1.289-1.452 2.188-3.146 2.188-5.343c0-2.15-1.215-3.955-2.874-4.713c-1.612-.737-3.778-.542-5.836 1.597a.75.75 0 0 1-1.08 0C9.402 3.882 7.236 3.687 5.624 4.424M12 4.46C9.688 2.39 7.099 2.1 5 3.059C2.786 4.074 1.25 6.426 1.25 9.138c0 2.665 1.11 4.699 2.567 6.339c1.166 1.313 2.593 2.412 3.854 3.382c.286.22.563.434.826.642c.513.404 1.063.834 1.62 1.16c.557.325 1.193.59 1.883.59s1.326-.265 1.883-.59c.558-.326 1.107-.756 1.62-1.16a78.6 78.6 0 0 1 .826-.642c1.26-.97 2.688-2.07 3.854-3.382c1.457-1.64 2.567-3.674 2.567-6.339c0-2.712-1.535-5.064-3.75-6.077c-2.099-.96-4.688-.67-7 1.399" clip-rule="evenodd" />
    </symbol>
    <symbol id="cart" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
        <path fill="currentColor" fill-rule="evenodd" d="M2.249 2.292a.75.75 0 1 0-.498 1.416l.262.091c.667.235 1.106.39 1.429.549c.303.149.437.27.525.398c.09.132.16.314.2.677c.04.38.041.875.041 1.615V9.76c0 1.453.014 2.5.151 3.3c.146.854.438 1.466.985 2.042c.594.627 1.346.9 2.243 1.026c.858.122 1.948.122 3.293.122h5.406c.742 0 1.366 0 1.87-.062c.537-.065 1.025-.209 1.452-.556c.426-.348.665-.797.837-1.309c.163-.482.289-1.093.439-1.82l.508-2.469l.002-.005l.01-.052c.165-.825.303-1.519.338-2.077c.036-.586-.031-1.164-.413-1.66c-.235-.306-.565-.479-.866-.584a4.617 4.617 0 0 0-1.002-.21c-.687-.076-1.522-.076-2.34-.076H5.667a5.932 5.932 0 0 0-.01-.108c-.054-.497-.17-.95-.453-1.362c-.284-.416-.662-.682-1.102-.899c-.412-.202-.936-.386-1.553-.603zm3.46 4.578h11.38c.856 0 1.61.001 2.205.067c.296.034.517.08.672.134a.56.56 0 0 1 .176.086c.062.082.128.23.102.651c-.027.444-.143 1.036-.321 1.926v.002l-.5 2.42c-.16.783-.27 1.303-.399 1.688c-.123.366-.239.523-.364.625c-.125.102-.303.184-.685.23c-.404.05-.935.051-1.734.051h-5.303c-1.417 0-2.4-.002-3.14-.107c-.716-.101-1.093-.285-1.366-.573c-.32-.338-.493-.668-.595-1.263c-.11-.65-.129-1.558-.129-3.047zM7.5 21.75a2.25 2.25 0 1 1 0-4.5a2.25 2.25 0 0 1 0 4.5m-.75-2.25a.75.75 0 1 0 1.5 0a.75.75 0 0 0-1.5 0m9.75 2.25a2.25 2.25 0 1 1 0-4.5a2.25 2.25 0 0 1 0 4.5m-.75-2.25a.75.75 0 1 0 1.5 0a.75.75 0 0 0-1.5 0" clip-rule="evenodd" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="alt-arrow-right-outline" viewBox="0 0 24 24">
        <path fill="currentColor" fill-rule="evenodd" d="M8.512 4.43a.75.75 0 0 1 1.057.082l6 7a.75.75 0 0 1 0 .976l-6 7a.75.75 0 0 1-1.138-.976L14.012 12L8.431 5.488a.75.75 0 0 1 .08-1.057" clip-rule="evenodd" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="alt-arrow-left-outline" viewBox="0 0 24 24">
        <path fill="currentColor" fill-rule="evenodd" d="M15.488 4.43a.75.75 0 0 1 .081 1.058L9.988 12l5.581 6.512a.75.75 0 1 1-1.138.976l-6-7a.75.75 0 0 1 0-.976l6-7a.75.75 0 0 1 1.057-.081" clip-rule="evenodd" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="cart-outline" viewBox="0 0 16 16">
        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="quality" viewBox="0 0 16 16">
        <path d="M9.669.864 8 0 6.331.864l-1.858.282-.842 1.68-1.337 1.32L2.6 6l-.306 1.854 1.337 1.32.842 1.68 1.858.282L8 12l1.669-.864 1.858-.282.842-1.68 1.337-1.32L13.4 6l.306-1.854-1.337-1.32-.842-1.68L9.669.864zm1.196 1.193.684 1.365 1.086 1.072L12.387 6l.248 1.506-1.086 1.072-.684 1.365-1.51.229L8 10.874l-1.355-.702-1.51-.229-.684-1.365-1.086-1.072L3.614 6l-.25-1.506 1.087-1.072.684-1.365 1.51-.229L8 1.126l1.356.702 1.509.229z" />
        <path d="M4 11.794V16l4-1 4 1v-4.206l-2.018.306L8 13.126 6.018 12.1 4 11.794z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="price-tag" viewBox="0 0 16 16">
        <path d="M6 4.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm-1 0a.5.5 0 1 0-1 0 .5.5 0 0 0 1 0z" />
        <path d="M2 1h4.586a1 1 0 0 1 .707.293l7 7a1 1 0 0 1 0 1.414l-4.586 4.586a1 1 0 0 1-1.414 0l-7-7A1 1 0 0 1 1 6.586V2a1 1 0 0 1 1-1zm0 5.586 7 7L13.586 9l-7-7H2v4.586z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="shield-plus" viewBox="0 0 16 16">
        <path d="M5.338 1.59a61.44 61.44 0 0 0-2.837.856.481.481 0 0 0-.328.39c-.554 4.157.726 7.19 2.253 9.188a10.725 10.725 0 0 0 2.287 2.233c.346.244.652.42.893.533.12.057.218.095.293.118a.55.55 0 0 0 .101.025.615.615 0 0 0 .1-.025c.076-.023.174-.061.294-.118.24-.113.547-.29.893-.533a10.726 10.726 0 0 0 2.287-2.233c1.527-1.997 2.807-5.031 2.253-9.188a.48.48 0 0 0-.328-.39c-.651-.213-1.75-.56-2.837-.855C9.552 1.29 8.531 1.067 8 1.067c-.53 0-1.552.223-2.662.524zM5.072.56C6.157.265 7.31 0 8 0s1.843.265 2.928.56c1.11.3 2.229.655 2.887.87a1.54 1.54 0 0 1 1.044 1.262c.596 4.477-.787 7.795-2.465 9.99a11.775 11.775 0 0 1-2.517 2.453 7.159 7.159 0 0 1-1.048.625c-.28.132-.581.24-.829.24s-.548-.108-.829-.24a7.158 7.158 0 0 1-1.048-.625 11.777 11.777 0 0 1-2.517-2.453C1.928 10.487.545 7.169 1.141 2.692A1.54 1.54 0 0 1 2.185 1.43 62.456 62.456 0 0 1 5.072.56z" />
        <path d="M8 4.5a.5.5 0 0 1 .5.5v1.5H10a.5.5 0 0 1 0 1H8.5V9a.5.5 0 0 1-1 0V7.5H6a.5.5 0 0 1 0-1h1.5V5a.5.5 0 0 1 .5-.5z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="star-fill" viewBox="0 0 24 24">
        <path fill="currentColor" d="M9.153 5.408C10.42 3.136 11.053 2 12 2c.947 0 1.58 1.136 2.847 3.408l.328.588c.36.646.54.969.82 1.182c.28.213.63.292 1.33.45l.636.144c2.46.557 3.689.835 3.982 1.776c.292.94-.546 1.921-2.223 3.882l-.434.507c-.476.557-.715.836-.822 1.18c-.107.345-.071.717.001 1.46l.066.677c.253 2.617.38 3.925-.386 4.506c-.766.582-1.918.051-4.22-1.009l-.597-.274c-.654-.302-.981-.452-1.328-.452c-.347 0-.674.15-1.328.452l-.596.274c-2.303 1.06-3.455 1.59-4.22 1.01c-.767-.582-.64-1.89-.387-4.507l.066-.676c.072-.744.108-1.116 0-1.46c-.106-.345-.345-.624-.821-1.18l-.434-.508c-1.677-1.96-2.515-2.941-2.223-3.882c.293-.941 1.523-1.22 3.983-1.776l.636-.144c.699-.158 1.048-.237 1.329-.45c.28-.213.46-.536.82-1.182z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="star-empty" viewBox="0 0 16 16">
        <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="star-half" viewBox="0 0 16 16">
        <path d="M5.354 5.119 7.538.792A.516.516 0 0 1 8 .5c.183 0 .366.097.465.292l2.184 4.327 4.898.696A.537.537 0 0 1 16 6.32a.548.548 0 0 1-.17.445l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256a.52.52 0 0 1-.146.05c-.342.06-.668-.254-.6-.642l.83-4.73L.173 6.765a.55.55 0 0 1-.172-.403.58.58 0 0 1 .085-.302.513.513 0 0 1 .37-.245l4.898-.696zM8 12.027a.5.5 0 0 1 .232.056l3.686 1.894-.694-3.957a.565.565 0 0 1 .162-.505l2.907-2.77-4.052-.576a.525.525 0 0 1-.393-.288L8.001 2.223 8 2.226v9.8z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="quote" viewBox="0 0 24 24">
        <path fill="currentColor" d="m15 17l2-4h-4V6h7v7l-2 4h-3Zm-9 0l2-4H4V6h7v7l-2 4H6Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="facebook" viewBox="0 0 24 24">
        <path fill="currentColor" d="M9.198 21.5h4v-8.01h3.604l.396-3.98h-4V7.5a1 1 0 0 1 1-1h3v-4h-3a5 5 0 0 0-5 5v2.01h-2l-.396 3.98h2.396v8.01Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="youtube" viewBox="0 0 32 32">
        <path fill="currentColor" d="M29.41 9.26a3.5 3.5 0 0 0-2.47-2.47C24.76 6.2 16 6.2 16 6.2s-8.76 0-10.94.59a3.5 3.5 0 0 0-2.47 2.47A36.13 36.13 0 0 0 2 16a36.13 36.13 0 0 0 .59 6.74a3.5 3.5 0 0 0 2.47 2.47c2.18.59 10.94.59 10.94.59s8.76 0 10.94-.59a3.5 3.5 0 0 0 2.47-2.47A36.13 36.13 0 0 0 30 16a36.13 36.13 0 0 0-.59-6.74ZM13.2 20.2v-8.4l7.27 4.2Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="twitter" viewBox="0 0 256 256">
        <path fill="currentColor" d="m245.66 77.66l-29.9 29.9C209.72 177.58 150.67 232 80 232c-14.52 0-26.49-2.3-35.58-6.84c-7.33-3.67-10.33-7.6-11.08-8.72a8 8 0 0 1 3.85-11.93c.26-.1 24.24-9.31 39.47-26.84a110.93 110.93 0 0 1-21.88-24.2c-12.4-18.41-26.28-50.39-22-98.18a8 8 0 0 1 13.65-4.92c.35.35 33.28 33.1 73.54 43.72V88a47.87 47.87 0 0 1 14.36-34.3A46.87 46.87 0 0 1 168.1 40a48.66 48.66 0 0 1 41.47 24H240a8 8 0 0 1 5.66 13.66Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="instagram" viewBox="0 0 256 256">
        <path fill="currentColor" d="M128 80a48 48 0 1 0 48 48a48.05 48.05 0 0 0-48-48Zm0 80a32 32 0 1 1 32-32a32 32 0 0 1-32 32Zm48-136H80a56.06 56.06 0 0 0-56 56v96a56.06 56.06 0 0 0 56 56h96a56.06 56.06 0 0 0 56-56V80a56.06 56.06 0 0 0-56-56Zm40 152a40 40 0 0 1-40 40H80a40 40 0 0 1-40-40V80a40 40 0 0 1 40-40h96a40 40 0 0 1 40 40ZM192 76a12 12 0 1 1-12-12a12 12 0 0 1 12 12Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="linkedin" viewBox="0 0 24 24">
        <path fill="currentColor" d="M6.94 5a2 2 0 1 1-4-.002a2 2 0 0 1 4 .002zM7 8.48H3V21h4V8.48zm6.32 0H9.34V21h3.94v-6.57c0-3.66 4.77-4 4.77 0V21H22v-7.93c0-6.17-7.06-5.94-8.72-2.91l.04-1.68z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="nav-icon" viewBox="0 0 16 16">
        <path d="M14 10.5a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0 0 1h3a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-7a.5.5 0 0 0 0 1h7a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-11a.5.5 0 0 0 0 1h11a.5.5 0 0 0 .5-.5z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="close" viewBox="0 0 16 16">
        <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="navbar-icon" viewBox="0 0 16 16">
        <path d="M14 10.5a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0 0 1h3a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-7a.5.5 0 0 0 0 1h7a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-11a.5.5 0 0 0 0 1h11a.5.5 0 0 0 .5-.5z" />
    </symbol>
    </svg>

    <div class="search-popup">
        <div class="search-popup-container">

            <form role="search" method="get" class="search-form" action="filter">
                <input type="search" id="search-form" class="search-field" placeholder="Type and press enter" value="" name="s" />
                <button type="submit" class="search-submit"><svg class="search"><use xlink:href="#search"></use></svg></button>
            </form>

            <h5 class="cat-list-title">Thể loại sách</h5>

            <ul class="cat-list">
                <li class="cat-list-item">
                    <a href="shop"  title="">Tất cả</a>
                </li>
                <c:forEach items="${category}" var="cate">
                    <li class="cat-list-item">
                        <a href="filter?categoryId=${cate.categoryId}"  title="">${cate.categoryName}</a>
                    </li>
                </c:forEach>

            </ul>

        </div>
    </div>

    <header id="header" class="site-header">

        <div class="top-info border-bottom d-none d-md-block ">
            <div class="container-fluid">
                <div class="row g-0">
                    <div class="col-md-4">
                        <p class="fs-6 my-2 text-center">Bạn cần giúp đỡ? Hãy gọi cho chúng tôi  <a href="#">+84 38 272 0127</a></p>
                    </div>
                    <div class="col-md-4 border-start border-end">
                        <p class="fs-6 my-2 text-center">Đang có giảm giá lên tới 60% <a class="text-decoration-underline" href="shop">Mua ngay!!</a></p>
                    </div>
                    <div class="col-md-4">
                        <p class="fs-6 my-2 text-center">Giao hàng 2-3 ngày làm việc và trả lại miễn phí</p>
                    </div>
                </div>
            </div>
        </div>

        <nav id="header-nav" class="navbar navbar-expand-lg py-3">
            <div class="container">
                <a class="navbar-brand" href="home">
                    <img src="images/anh456.png" class="logo" style="width: 12rem; height: auto">
                </a>
                <button class="navbar-toggler d-flex d-lg-none order-3 p-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#bdNavbar" aria-controls="bdNavbar" aria-expanded="false" aria-label="Toggle navigation">
                    <svg class="navbar-icon">
                    <use xlink:href="#navbar-icon"></use>
                    </svg>
                </button>
                <div class="offcanvas offcanvas-end" tabindex="-1" id="bdNavbar" aria-labelledby="bdNavbarOffcanvasLabel">
                    <div class="offcanvas-header px-4 pb-0">
                        <a class="navbar-brand" href="home">
                            <img src="${pageContext.request.contextPath}/images/main-logo.png" class="logo">
                        </a>
                        <button type="button" class="btn-close btn-close-black" data-bs-dismiss="offcanvas" aria-label="Close" data-bs-target="#bdNavbar"></button>
                    </div>
                    <div class="offcanvas-body">
                        <ul id="navbar" class="navbar-nav text-uppercase justify-content-start justify-content-lg-center align-items-start align-items-lg-center flex-grow-1">
                            <li class="nav-item">
                                <a class="nav-link me-4" href="home">Trang chủ</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link me-4" href="about">Giới thiệu</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link me-4" href="shop">Sản phẩm</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active me-4" href="blog">Tin tức</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link me-4 dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Trang</a>
                                <ul class="dropdown-menu animate slide border">
                                    <li>
                                        <a href="about" class="dropdown-item fw-light">Giới thiệu <span class="badge bg-primary"></span></a>
                                    </li>
                                    <li>
                                        <a href="shop" class="dropdown-item fw-light">Sản phẩm <span class="badge bg-primary"></span></a>
                                    </li>

                                    <li>
                                        <a href="cart" class="dropdown-item fw-light">Giỏ hàng <span class="badge bg-primary"></span></a>
                                    </li>
                                    <li>
                                        <a href="check" class="dropdown-item fw-light">Thanh toán <span class="badge bg-primary"></span></a>
                                    </li>
                                    <li>
                                        <a href="blog" class="dropdown-item active fw-light">Tin tức <span class="badge bg-primary"></span></a>
                                    </li>

                                    <li>
                                        <a href="contact" class="dropdown-item fw-light">Liên hệ <span class="badge bg-primary"></span></a>
                                    </li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link  me-4" href="contact">Liên hệ</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-decoration-underline me-4" href="https://templatesjungle.gumroad.com/l/bookly-bookstore-ecommerce-bootstrap-html-css-website-template" target="_blank"></a>
                            </li>
                        </ul>
                        <div class="user-items d-flex">
                            <ul class="d-flex justify-content-end list-unstyled mb-0">
                                <li class="search-item pe-3">
                                    <a href="#" class="search-button">
                                        <svg class="search">
                                        <use xlink:href="#search"></use>
                                        </svg>
                                    </a>
                                </li>
                                <c:choose>
                                    <c:when test="${sessionScope.account eq null}">
                                        <li class="pe-3">
                                            <a href="login">
                                                <svg class="user">
                                                <use xlink:href="#user"></use>
                                                </svg>
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="pe-3">
                                            <div class="nav-user-dropdown">
                                                <button class="dropbtn">${sessionScope.account.getUserName()}</button>
                                                <div class="dropdown-content">
                                                    <a href="profile">Tài khoản của tôi</a>
                                                    <a href="#">Đơn mua</a>
                                                    <a href="logout">Đăng xuất</a>
                                                </div>
                                            </div>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <li class="wishlist-dropdown dropdown pe-3">
                                    <div class="dropdown-menu animate slide dropdown-menu-start dropdown-menu-lg-end p-3">
                                        <h4 class="d-flex justify-content-between align-items-center mb-3">
                                            <span class="text-primary">Your wishlist</span>
                                            <span class="badge bg-primary rounded-pill">2</span>
                                        </h4>
                                        <ul class="list-group mb-3">
                                            <li class="list-group-item bg-transparent d-flex justify-content-between lh-sm">
                                                <div>
                                                    <h5>
                                                        <a href="single">The Emerald Crown</a>
                                                    </h5>
                                                    <small>Special discounted price.</small>
                                                    <a href="#" class="d-block fw-medium text-capitalize mt-2">Add to cart</a>
                                                </div>
                                                <span class="text-primary">$2000</span>
                                            </li>
                                            <li class="list-group-item bg-transparent d-flex justify-content-between lh-sm">
                                                <div>
                                                    <h5>
                                                        <a href="single">The Last Enchantment</a>
                                                    </h5>
                                                    <small>Perfect for enlightened people.</small>
                                                    <a href="#" class="d-block fw-medium text-capitalize mt-2">Add to cart</a>
                                                </div>
                                                <span class="text-primary">$400</span>
                                            </li>
                                            <li class="list-group-item bg-transparent d-flex justify-content-between">
                                                <span class="text-capitalize"><b>Total (USD)</b></span>
                                                <strong>$1470</strong>
                                            </li>
                                        </ul>
                                        <div class="d-flex flex-wrap justify-content-center">
                                            <a href="#" class="w-100 btn btn-dark mb-1" type="submit">Add all to cart</a>
                                            <a href="cart" class="w-100 btn btn-primary" type="submit">View cart</a>
                                        </div>
                                    </div>
                                </li>
                                <li class="cart-dropdown dropdown">
                                    <a href="cart" class="dropdown-toggle" data-bs-toggle="dropdown" role="button" aria-expanded="false">
                                        <svg class="cart">
                                        <use xlink:href="#cart"></use>
                                        </svg><span class="fs-6 fw-light">(02)</span>
                                    </a>
                                    <div class="dropdown-menu animate slide dropdown-menu-start dropdown-menu-lg-end p-3">
                                        <h4 class="d-flex justify-content-between align-items-center mb-3">
                                            <span class="text-primary">Your cart</span>
                                            <span class="badge bg-primary rounded-pill">2</span>
                                        </h4>
                                        <ul class="list-group mb-3">
                                            <li class="list-group-item bg-transparent d-flex justify-content-between lh-sm">
                                                <div>
                                                    <h5>
                                                        <a href="single">Secrets of the Alchemist</a>
                                                    </h5>
                                                    <small>High quality in good price.</small>
                                                </div>
                                                <span class="text-primary">$870</span>
                                            </li>
                                            <li class="list-group-item bg-transparent d-flex justify-content-between lh-sm">
                                                <div>
                                                    <h5>
                                                        <a href="single">Quest for the Lost City</a>
                                                    </h5>
                                                    <small>Professional Quest for the Lost City.</small>
                                                </div>
                                                <span class="text-primary">$600</span>
                                            </li>
                                            <li class="list-group-item bg-transparent d-flex justify-content-between">
                                                <span class="text-capitalize"><b>Total (USD)</b></span>
                                                <strong>$1470</strong>
                                            </li>
                                        </ul>
                                        <div class="d-flex flex-wrap justify-content-center">
                                            <a href="cart" class="w-100 btn btn-dark mb-1" type="submit">View Cart</a>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    <div id="blog" class="padding-large">
        <div class="container">
            <div class="row flex-row-reverse g-md-5">
                <main class="col-md-9 mb-4 mb-md-0">
                    <div class="filter-blog d-flex flex-wrap justify-content-between mb-4">
                        <div class="showing-product">
                            <p>${requestScope.quantityNews} kết quả</p>
                        </div>
                        <div class="sort-by">
                            <select id="sorting" class="form-select" data-filter-sort="" data-filter-order="" onchange="location.href = 'blog?sort=' + this.value + '&id=${requestScope.tid}'">
                                <option value="decrease" <c:if test="${requestScope.sortNews eq 'decrease'}">selected</c:if>>Mới nhất</option>
                                <option value="increase" <c:if test="${requestScope.sortNews eq 'increase'}">selected</c:if>>Cũ nhất</option>
                            </select>
                        </div>
                    </div>
                    <div class="row post-contents">
                        <c:forEach var="news" items="${requestScope.listNews}">
                            <div class="col-lg-4 col-md-6 posts mb-5">
                                <div class="img-container" style="display: flex; justify-content: center; align-items: center;">
                                    <img src="${news.imgNews}" alt="post image" class="img-fluid rounded-3" style="height: 12em; width: auto; border-radius: 1em">
                                </div>
                                <a href="blog?id=${news.topic.topicId}" class="fs-6 text-primary">${news.topic.topicName}</a>
                                <h5 class="card-title mb-2 text-capitalize text-dark" style="font-weight: bold; font-size: 0.9em; color: #343a40; margin-bottom: 0.5em; text-transform: capitalize;">
                                    <a href="post?id=${news.newId}">${news.title}</a>
                                </h5>
                                <p class="mb-2" style="display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical; overflow: hidden;">
                                    ${news.content}
                                    <span><a class="text-decoration-underline text-black-50" href="post?id=${news.newId}">Read More</a></span>
                                </p>
                            </div>
                        </c:forEach>
                    </div>
                    <c:set var="total" value="${requestScope.endPage}" />
                    <c:set var="page" value="${requestScope.page}" />
                    <nav class="pt-5" aria-label="Page navigation">
                        <ul class="pagination justify-content-center gap-4">
                            <li class="page-item">
                                <c:if test="${page > 1}">
                                    <a class="page-link" href="blog?page=${page-1}&id=${requestScope.tid}&sort=${requestScope.sortNews}">Prev</a>
                                </c:if>
                            </li>
                            <c:if test="${total <= 5}">
                                <c:forEach begin="1" end="${total}" var="pageNum">
                                    <li class="page-item ${page == pageNum ? 'active' : ''}">
                                        <a class="page-link" href="blog?page=${pageNum}&id=${requestScope.tid}&sort=${requestScope.sortNews}" ${page == pageNum ? 'style="background-color: #F86D72; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>${pageNum}</a>
                                    </li>
                                </c:forEach>
                            </c:if>
                            <c:if test="${total > 5}">
                                <li class="page-item ${page == 1 ? 'active' : ''}">
                                    <a class="page-link" href="blog?page=1&id=${requestScope.tid}&sort=${requestScope.sortNews}" ${page == 1 ? 'style="background-color: #F86D72; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>1</a>
                                </li>
                                <c:if test="${page > 3}">
                                    <li class="page-item disabled">
                                        <span class="page-link">...</span>
                                    </li>
                                </c:if>
                                <c:forEach begin="${page > 2 ? page - 1 : 2}" end="${page < total - 1 ? page + 1 : total - 1}" var="pageNum">
                                    <li class="page-item ${page == pageNum ? 'active' : ''}">
                                        <a class="page-link" href="blog?page=${pageNum}&id=${requestScope.tid}&sort=${requestScope.sortNews}" ${page == pageNum ? 'style="background-color: #F86D72; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>${pageNum}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${page < total - 2}">
                                    <li class="page-item disabled">
                                        <span class="page-link">...</span>
                                    </li>
                                </c:if>
                                <li class="page-item ${page == total ? 'active' : ''}">
                                    <a class="page-link" href="blog?page=${total}&id=${requestScope.tid}&sort=${requestScope.sortNews}" ${page == total ? 'style="background-color: #F86D72; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>${total}</a>
                                </li>
                            </c:if>
                            <li class="page-item">
                                <c:if test="${page < total}">
                                    <a class="page-link" href="blog?page=${page+1}&id=${requestScope.tid}&sort=${requestScope.sortNews}">Next</a>
                                </c:if>
                            </li>
                        </ul>
                    </nav>
                </main>
                <aside class="col-md-3">
                    <div class="sidebar">
                        <div class="widget-product-categories pt-5">
                            <div class="section-title overflow-hidden mb-2">
                                <h3 class="d-flex flex-column mb-0">Danh mục</h3>
                            </div>
                            <ul class="product-categories mb-0 sidebar-list list-unstyled">
                                <li class="cat-item">
                                    <a href="blog?id=0" <c:if test="${requestScope.tid == topic.topicId}">style="color: #F86D72"</c:if>>Tất cả</a>
                                </li>
                                <c:forEach var="topic" items="${requestScope.listTopic}">
                                    <li class="cat-item">
                                        <a href="blog?id=${topic.topicId}" <c:if test="${requestScope.tid == topic.topicId}">style="color: #F86D72"</c:if>>${topic.topicName}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </aside>
            </div>
        </div>
    </div>
    <section id="instagram">
        <div class="container padding-large pb-0">
            <div class="text-center mb-4">
                <h3>Instagram</h3>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <figure class="instagram-item position-relative rounded-3">
                        <a href="https://templatesjungle.com/" class="image-link position-relative">
                            <div class="icon-overlay position-absolute d-flex justify-content-center">
                                <svg class="instagram">
                                <use xlink:href="#instagram"></use>
                                </svg>
                            </div>
                            <img src="${pageContext.request.contextPath}/images/insta-item1.jpg" alt="instagram" class="img-fluid rounded-3 insta-image">
                        </a>
                    </figure>
                </div>
                <div class="col-md-2">
                    <figure class="instagram-item position-relative rounded-3">
                        <a href="https://templatesjungle.com/" class="image-link position-relative">
                            <div class="icon-overlay position-absolute d-flex justify-content-center">
                                <svg class="instagram">
                                <use xlink:href="#instagram"></use>
                                </svg>
                            </div>
                            <img src="${pageContext.request.contextPath}/images/insta-item2.jpg" alt="instagram" class="img-fluid rounded-3 insta-image">
                        </a>
                    </figure>
                </div>
                <div class="col-md-2">
                    <figure class="instagram-item position-relative rounded-3">
                        <a href="https://templatesjungle.com/" class="image-link position-relative">
                            <div class="icon-overlay position-absolute d-flex justify-content-center">
                                <svg class="instagram">
                                <use xlink:href="#instagram"></use>
                                </svg>
                            </div>
                            <img src="${pageContext.request.contextPath}/images/insta-item3.jpg" alt="instagram" class="img-fluid rounded-3 insta-image">
                        </a>
                    </figure>
                </div>
                <div class="col-md-2">
                    <figure class="instagram-item position-relative rounded-3">
                        <a href="https://templatesjungle.com/" class="image-link position-relative">
                            <div class="icon-overlay position-absolute d-flex justify-content-center">
                                <svg class="instagram">
                                <use xlink:href="#instagram"></use>
                                </svg>
                            </div>
                            <img src="${pageContext.request.contextPath}/images/insta-item4.jpg" alt="instagram" class="img-fluid rounded-3 insta-image">
                        </a>
                    </figure>
                </div>
                <div class="col-md-2">
                    <figure class="instagram-item position-relative rounded-3">
                        <a href="https://templatesjungle.com/" class="image-link position-relative">
                            <div class="icon-overlay position-absolute d-flex justify-content-center">
                                <svg class="instagram">
                                <use xlink:href="#instagram"></use>
                                </svg>
                            </div>
                            <img src="${pageContext.request.contextPath}/images/insta-item5.jpg" alt="instagram" class="img-fluid rounded-3 insta-image">
                        </a>
                    </figure>
                </div>
                <div class="col-md-2">
                    <figure class="instagram-item position-relative rounded-3">
                        <a href="https://templatesjungle.com/" class="image-link position-relative">
                            <div class="icon-overlay position-absolute d-flex justify-content-center">
                                <svg class="instagram">
                                <use xlink:href="#instagram"></use>
                                </svg>
                            </div>
                            <img src="${pageContext.request.contextPath}/images/insta-item6.jpg" alt="instagram" class="img-fluid rounded-3 insta-image">
                        </a>
                    </figure>
                </div>
            </div>
        </div>
    </section>
    <footer id="footer" class="padding-large">
        <div class="container">
            <div class="row">
                <div class="footer-top-area">
                    <div class="row d-flex flex-wrap justify-content-between">
                        <div class="col-lg-3 col-sm-6 pb-3">
                            <div class="footer-menu">
                                <img src="images/anh456.png" alt="logo" class="img-fluid mb-2">
                                <p>
                                    "Tôi đọc lòi cả mắt và vẫn không đọc được tới một nửa... người ta càng đọc nhiều, người ta càng thấy còn nhiều điều cần phải đọc.” John Adams
                                </p>
                            </div>
                        </div>
                        <div class="col-lg-2 col-sm-6 pb-3">
                            <div class="footer-menu text-capitalize">
                                <h5 class="widget-title pb-2">Trang chính</h5>
                                <ul class="menu-list list-unstyled text-capitalize">
                                    <li class="menu-item mb-1">
                                        <a href="home">Trang chủ</a>
                                    </li>
                                    <li class="menu-item mb-1">
                                        <a href="about">Giới thiệu</a>
                                    </li>
                                    <li class="menu-item mb-1">
                                        <a href="shop">Sản phẩm</a>
                                    </li>
                                    <li class="menu-item mb-1">
                                        <a href="blog">Tin tức</a>
                                    </li>
                                    <li class="menu-item mb-1">
                                        <a href="contact">Liên hệ</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 pb-3">
                            <div class="footer-menu text-capitalize">
                                <h5 class="widget-title pb-2">Trợ giúp & Thông tin Trợ giúp</h5>
                                <ul class="menu-list list-unstyled">
                                    <li class="menu-item mb-1">
                                        <a href="#">Theo dõi đơn hàng của bạn</a>
                                    </li>
                                    <li class="menu-item mb-1">
                                        <a href="#">Chính sách hoàn trả</a>
                                    </li>
                                    <li class="menu-item mb-1">
                                        <a href="#">Vận chuyển + Giao hàng</a>
                                    </li>
                                    <li class="menu-item mb-1">
                                        <a href="#">Liên hệ chúng tôi</a>
                                    </li>
                                    <li class="menu-item mb-1">
                                        <a href="#">Câu hỏi thường gặp</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 pb-3">
                            <div class="footer-menu contact-item">
                                <h5 class="widget-title text-capitalize pb-2">Liên hệ chúng tôi</h5>
                                <p>Bạn có bất kỳ thắc mắc hoặc gợi ý nào không? <a href="mailto:" class="text-decoration-underline">shopbook88@gmail.com</a></p>
                                <p>Nếu bạn cần hỗ trợ? Chỉ cần gọi cho chúng tôi. <a href="#" class="text-decoration-underline">+84 38 272 0127</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <hr>
    <div id="footer-bottom" class="mb-2">
        <div class="container">
            <div class="d-flex flex-wrap justify-content-between">
                <div class="ship-and-payment d-flex gap-md-5 flex-wrap">
                    <div class="shipping d-flex">
                        <p>Giao hàng nhanh</p>
                        <div class="card-wrap ps-2">
                        </div>
                    </div>
                    <div class="payment-method d-flex">
                        <p>Thanh toán trực tiếp hoặc qua các thẻ</p>
                        <div class="card-wrap ps-2">
                            <img src="${pageContext.request.contextPath}/images/visa.jpg" alt="visa">
                            <img src="${pageContext.request.contextPath}/images/mastercard.jpg" alt="mastercard">
                            <img src="${pageContext.request.contextPath}/images/paypal.jpg" alt="paypal">
                        </div>
                    </div>
                </div>
                <div class="copyright">
                    <p>ShopBook88 <a href="home" target="_blank">mang lại thế giới cho bạn</a>
                    </p>
                </div>
            </div>
        </div>
    </div>

    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
</body>
</html>
