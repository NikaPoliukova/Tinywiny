import axios from "axios";
import {Buffer} from "buffer";

class ImageService{
    async getImage(): Promise<any> {
        const response = await axios.get<any>('http://localhost:8080/api/v1/file', {
            withCredentials: true,
            responseType: 'arraybuffer'
        });
        return Buffer.from(response.data, 'binary').toString('base64');
    }
    async getProductImage(productId : number):Promise<any> {
        const response = await axios.get<any>('http://localhost:8080/api/v1/file/'+productId, {
            withCredentials: true});
        return response.data;
    }

    deleteImage = async (productId : number) => {
        const response = await axios.delete(`http://localhost:8080/api/v1/admin/image/` +productId, {
            withCredentials: true
        });
        return response.data;
    }
    uploadImage = async ({productId, file}: any): Promise<any>  => {
        const formData = new FormData();
        formData.append('productId', productId);
        formData.append('file', file, file.name);
        const response = await axios.post('http://localhost:8080/api/v1/file', formData,{
            withCredentials: true,
            headers: {
                "Content-Type": "multipart/form-data",
            },
            });
        return Buffer.from(response.data, 'binary').toString('base64');
    }
}
export default new ImageService();