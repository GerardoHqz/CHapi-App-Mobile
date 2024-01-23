const nodemailer = require('nodemailer');

exports.transporter = nodemailer.createTransport({
        host: 'smtp.gmail.com',
        port: 465,
        secure: true, // true for 465, false for other ports
        auth: {
            user: 'noreply.chapi@gmail.com', // generated ethereal user
            pass: 'hjrdslqpoqmsibcv' // generated ethereal password
        },
});
