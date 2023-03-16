import axios from "axios";
import {Buffer} from "buffer";

class ImageService{
    async downloadImage(): Promise<any> {
        const response = await axios.get<any>('http://localhost:8080/api/v1/file', {
            withCredentials: true,
            responseType: 'arraybuffer'
        });
        return Buffer.from(response.data, 'binary').toString('base64');
    }
    deleteImage = async (productId : number) => {
        const response = await axios.delete(`http://localhost:8080/api/v1/admin/image/` +productId, {
            withCredentials: true
        });
        return response.data;
    }
    uploadImage = async (image: any) => {
        const response = await axios.post('http://localhost:8080/api/v1/files/',
            image, {
                withCredentials: true,
                headers: {
                    'Content-Type': `multipart/form-data; boundary=<calculated when request is sent>`,
                },
            });
        return response.data.message;
    }
}
export default new ImageService();