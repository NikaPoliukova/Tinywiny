declare module 'js-cookie' {
    export function get(name: string): string;
    export function set(name: string, value: string, options?: any): void;
    // add additional functions and variables as needed
}